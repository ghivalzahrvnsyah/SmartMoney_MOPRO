package com.d3if3020.smartmoney.view

import BankListAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.d3if3020.smartmoney.databinding.FragmentBankListBinding
import com.d3if3020.smartmoney.network.ApiStatus
import com.d3if3020.smartmoney.viewmodel.BankListViewModel

class BankListFragment : Fragment() {
    private val viewModel: BankListViewModel by lazy {
        ViewModelProvider(this).get(BankListViewModel::class.java)
    }

    private lateinit var binding: FragmentBankListBinding
    private lateinit var bankListAdapter: BankListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBankListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bankListAdapter = BankListAdapter()

        binding.rvBankList.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            adapter = bankListAdapter
            setHasFixedSize(true)
        }

        viewModel.getData().observe(viewLifecycleOwner, { bankList ->
            bankListAdapter.submitList(bankList)
        })

        viewModel.getStatus().observe(viewLifecycleOwner, { status ->
            updateProgress(status)
        })
    }

    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.networkError.visibility = View.GONE
            }
            ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.GONE
            }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }
}
