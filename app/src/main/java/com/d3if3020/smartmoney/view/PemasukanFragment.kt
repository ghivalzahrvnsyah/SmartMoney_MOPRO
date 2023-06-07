package com.d3if3020.smartmoney.view

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.d3if3020.smartmoney.PreferencesManager
import com.d3if3020.smartmoney.R
import com.d3if3020.smartmoney.databinding.FragmentPemasukanBinding
import com.d3if3020.smartmoney.db.DataDb
import com.d3if3020.smartmoney.utils.FunctionHelper
import com.d3if3020.smartmoney.viewmodel.PemasukanViewModel
import com.d3if3020.smartmoney.viewmodel.PemasukanViewModelFactory

class PemasukanFragment : Fragment() {
    private val viewModel: PemasukanViewModel by lazy {
        val db = DataDb.getInstance(requireContext())
        val factory = PemasukanViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[PemasukanViewModel::class.java]
    }

    private lateinit var binding: FragmentPemasukanBinding
    private lateinit var myAdapter: PemasukanAdapter
    private lateinit var pemasukanViewModel: PemasukanViewModel
    private lateinit var preferencesManager: PreferencesManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        preferencesManager = PreferencesManager(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPemasukanBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myAdapter = PemasukanAdapter()

        binding.rvPemasukan.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = myAdapter
        }

        viewModel.data.observe(viewLifecycleOwner) { pemasukanList ->
            myAdapter.submitList(pemasukanList)
            val totalPemasukan = myAdapter.getTotalPemasukan()
            binding.tvPemasukanTotal.text = FunctionHelper.formatPrice(totalPemasukan)
        }

        binding.fabAddPemasukan.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addDataFragment2)
        }

        binding.btnHapus.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(requireContext())
            alertDialogBuilder.setTitle("Konfirmasi")
            alertDialogBuilder.setMessage("Anda yakin ingin menghapus semua data?")
            alertDialogBuilder.setPositiveButton("Ya") { dialog, _ ->
                viewModel.hapusSemuaPemasukan()
                Toast.makeText(requireContext(), "Semua data berhasil dihapus", Toast.LENGTH_SHORT)
                    .show()
                dialog.dismiss()
            }
            alertDialogBuilder.setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }

    }

}