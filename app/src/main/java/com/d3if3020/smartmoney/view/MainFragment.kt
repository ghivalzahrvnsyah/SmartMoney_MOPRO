package com.d3if3020.smartmoney.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.d3if3020.smartmoney.R
import com.d3if3020.smartmoney.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private lateinit var pemasukanFragment: PemasukanFragment
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //super.onViewCreated(view, savedInstanceState)

        // Create an instance of the ViewPagerAdapter
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val pagerAdapter = FragmentAdapter(childFragmentManager)
        pagerAdapter.addFragment(PemasukanFragment(), "Pemasukan")
        pagerAdapter.addFragment(PengeluaranFragment(), "Pengeluaran")

        // Set the adapter to the ViewPager
        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about) {
            findNavController().navigate(
                R.id.action_mainFragment_to_aboutFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}

