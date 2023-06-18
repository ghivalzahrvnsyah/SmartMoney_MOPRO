package com.d3if3020.smartmoney.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.d3if3020.smartmoney.databinding.FragmentBankListBinding
import com.d3if3020.smartmoney.viewmodel.BankListViewModel

class BankListFragment : Fragment() {
    private lateinit var binding: FragmentBankListBinding
    private lateinit var bankListAdapter: BankListAdapter
    private lateinit var bankListViewModel: BankListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBankListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bankListAdapter = BankListAdapter()
        binding.rvBankList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = bankListAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }

        bankListViewModel = ViewModelProvider(this).get(BankListViewModel::class.java)
        bankListViewModel.retrieveData()
    }
}
