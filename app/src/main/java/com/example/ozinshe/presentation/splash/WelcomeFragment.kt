package com.example.ozinshe.presentation.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.ozinshe.R
import com.example.ozinshe.data.WelcomePageInfoList
import com.example.ozinshe.databinding.FragmentWelcomeBinding
import com.example.ozinshe.domain.adapter.WelcomePageAdapter
import com.example.ozinshe.domain.utils.provideNavigationHost

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding
    private lateinit var viewPager2: ViewPager2
    private lateinit var navController: NavController

    override fun onStart() {
        super.onStart()
        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
        }
    }

    override fun onResume() {
        super.onResume()
        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
        }
    }

    override fun onPause() {
        super.onPause()
        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        provideNavigationHost()?.apply {
            setNavigationVisibility(true)
        }
        setupViewPager(binding)

        binding.btnSkipWelcomeFragment.setOnClickListener {
            transactionInLogIn()
        }

        binding.btnNextInLogInPage.setOnClickListener {
            transactionInLogIn()
        }
    }

    private fun transactionInLogIn() {
        navController = findNavController()
        navController.navigate(R.id.action_welcomeFragment_to_loginFragment)
    }

    private fun setupViewPager(binding: FragmentWelcomeBinding) {
        val adapter = WelcomePageAdapter(welcomePageInfoList = WelcomePageInfoList.welcomePageSlidesInfoList)
        viewPager2 = binding.viewPager
        viewPager2.adapter = adapter

        viewPager2.registerOnPageChangeCallback(pager2Callback)
        binding.dotsIndicator.setViewPager2(viewPager2)
    }

    private val pager2Callback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            if (position == WelcomePageInfoList.welcomePageSlidesInfoList.size - 1) {
                binding.btnNextInLogInPage.visibility = View.VISIBLE
                binding.btnSkipWelcomeFragment.visibility = View.INVISIBLE
            } else {
                binding.btnNextInLogInPage.visibility = View.INVISIBLE
                binding.btnSkipWelcomeFragment.visibility = View.VISIBLE
            }
        }
    }
}