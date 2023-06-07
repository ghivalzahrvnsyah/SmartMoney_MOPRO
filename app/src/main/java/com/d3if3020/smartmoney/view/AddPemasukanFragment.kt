package com.d3if3020.smartmoney.view

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.d3if3020.smartmoney.R
import com.d3if3020.smartmoney.databinding.FragmentAddDataBinding
import com.d3if3020.smartmoney.db.DataDb
import com.d3if3020.smartmoney.model.Pemasukan
import com.d3if3020.smartmoney.utils.FunctionHelper.formatDate
import com.d3if3020.smartmoney.viewmodel.PemasukanViewModel
import com.d3if3020.smartmoney.viewmodel.PemasukanViewModelFactory
import java.util.*

class AddPemasukanFragment : Fragment() {
    private lateinit var binding: FragmentAddDataBinding

    private val viewModel: PemasukanViewModel by lazy {
        val db = DataDb.getInstance(requireContext())
        val factory = PemasukanViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[PemasukanViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddDataBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSimpan.setOnClickListener(){
            tambahData()
        }

        binding.tanggalInput.setOnClickListener {
            showDatePicker()
        }

    }
    fun tambahData(){
        val tanggal = binding.tanggalInput.text.toString()
        val jumlah_uang = binding.jumlahUangInput.text.toString()
        val keterangan = binding.keteranganInput.text.toString()
        if (TextUtils.isEmpty(tanggal) || TextUtils.isEmpty(jumlah_uang) || TextUtils.isEmpty(keterangan)) {
            Toast.makeText(requireContext(), "Data Tidak Boleh Kosong!", Toast.LENGTH_LONG).show()
        } else {
            viewModel.insertPemasukan(tanggal.toString(), jumlah_uang.toDouble(), keterangan)
        }

        findNavController().navigate(R.id.action_addDataFragment2_to_mainFragment)
        Toast.makeText(requireContext(), "Data Berhasil Ditambahkan!", Toast.LENGTH_LONG).show()

    }
    private fun showResult(result: Pemasukan?) {
        if (result == null) return
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Tambah Penghasilan")
        builder.setMessage(
            "Tanggal: ${result.tanggal}\n" +
                    "Jumlah Uang: ${result.jumlah_uang}\n" +
                    "Keterangan: ${result.keterangan}" +
                    "\nBerhasil ditambahkan!"
        )
        builder.setPositiveButton("Ok") { dialogInterface, which -> }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
    private fun showDatePicker() {
        val calendar: Calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                calendar.set(selectedYear, selectedMonth, selectedDayOfMonth)
                val formattedDate = formatDate(calendar)
                binding.tanggalInput.setText(formattedDate)
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }

}