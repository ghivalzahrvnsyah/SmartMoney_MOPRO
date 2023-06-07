package com.d3if3020.smartmoney.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.d3if3020.smartmoney.R
import com.d3if3020.smartmoney.databinding.FragmentPengeluaranBinding
import com.d3if3020.smartmoney.db.DataDb
import com.d3if3020.smartmoney.utils.FunctionHelper
import com.d3if3020.smartmoney.viewmodel.PengeluaranViewModel
import com.d3if3020.smartmoney.viewmodel.PengeluaranViewModelFactory

class PengeluaranFragment : Fragment() {
    private val viewModel: PengeluaranViewModel by lazy {
        val db = DataDb.getInstance(requireContext())
        val factory = PengeluaranViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[PengeluaranViewModel::class.java]
    }

    private lateinit var binding: FragmentPengeluaranBinding
    private lateinit var myAdapter: PengeluaranAdapter
    private lateinit var pengeluaranViewModel: PengeluaranViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPengeluaranBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myAdapter = PengeluaranAdapter()

        binding.rvPengeluaran.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = myAdapter
        }

        viewModel.data.observe(viewLifecycleOwner) { pengeluaranList ->
            myAdapter.submitList(pengeluaranList)
            val totalPengeluaran = myAdapter.getTotalPengeluaran()
            binding.tvPengeluaranTotal.text = FunctionHelper.formatPrice(totalPengeluaran)
        }

        binding.fabAddPengeluaran.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addPengeluaranFragment)
        }

        binding.btnHapus.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(requireContext())
            alertDialogBuilder.setTitle("Konfirmasi")
            alertDialogBuilder.setMessage("Anda yakin ingin menghapus semua data?")
            alertDialogBuilder.setPositiveButton("Ya") { dialog, _ ->
                viewModel.hapusSemuaPengeluaran()
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