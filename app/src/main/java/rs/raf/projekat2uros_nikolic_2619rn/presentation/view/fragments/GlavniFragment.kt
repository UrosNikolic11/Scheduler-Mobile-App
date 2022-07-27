package rs.raf.projekat2uros_nikolic_2619rn.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import rs.raf.projekat2uros_nikolic_2619rn.R
import rs.raf.projekat2uros_nikolic_2619rn.databinding.FragmentGlavniBinding
import rs.raf.projekat2uros_nikolic_2619rn.presentation.view.adapters.MainPagerAdapter

class GlavniFragment: Fragment(R.layout.fragment_glavni) {

    private var _binding: FragmentGlavniBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGlavniBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        binding.viewPager.adapter =
            MainPagerAdapter(
                parentFragmentManager,
                parentFragment?.requireContext()
            )
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val tmp = binding.viewPager.get(MainPagerAdapter.FRAGMENT_1).findFragment<Fragment>()
        val manager = parentFragmentManager.beginTransaction()
        manager.remove(tmp).commit()
        _binding = null
    }

}