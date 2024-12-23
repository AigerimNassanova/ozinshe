package com.example.ozinshe.presentation.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ozinshe.R
import com.example.ozinshe.databinding.FragmentSplashBinding
import com.example.ozinshe.databinding.FragmentWelcomeBinding
import com.example.ozinshe.domain.utils.provideNavigationHost
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

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
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val token = PreferenceProvider(requireContext()).getToken()

        lifecycleScope.launch {
            provideNavigationHost()?.apply {
                setNavigationVisibility(false)
            }
            lifecycleScope.launch {
                delay(3000)
                findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment)
            }
            /*if ("without_token" == token) {
                findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment)
            } else if (token != null && token.isNotEmpty()) {
                findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment)
            }*/
        }
    }
}
