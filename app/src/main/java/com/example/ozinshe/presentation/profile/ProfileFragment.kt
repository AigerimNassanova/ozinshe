package com.example.ozinshe.presentation.profile

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ozinshe.R
import com.example.ozinshe.data.SharedProvider
import com.example.ozinshe.databinding.FragmentProfileBinding
import com.example.ozinshe.domain.utils.provideNavigationHost
import com.example.ozinshe.presentation.profile.bottomsheet.SelectedLanguageBottomSheet
import kotlinx.coroutines.launch
import java.util.Locale

class ProfileFragment : Fragment(), OnLanguageSelectedListener {

    private lateinit var binding: FragmentProfileBinding

    private val viewModel: ProfileViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        provideNavigationHost()?.apply {
            setNavigationVisibility(true)
        }
    }

    override fun onResume() {
        super.onResume()
        provideNavigationHost()?.apply {
            setNavigationVisibility(true)
        }
    }

    override fun onPause() {
        super.onPause()
        provideNavigationHost()?.apply {
            setNavigationVisibility(true)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        provideNavigationHost()?.apply {
            setNavigationVisibility(true)
        }
        systemLanguage()

        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        if (Build.VERSION.SDK_INT >= 26) {
            transaction.setReorderingAllowed(false)
        }
        transaction.detach(this).attach(this).commit()

        binding.personalInfo.setOnClickListener {
            findNavController().navigate(R.id.editProfileFragment)
        }

        binding.changePassword.setOnClickListener {
            findNavController().navigate(R.id.changePasswordFragment)
        }

        val token = SharedProvider(requireContext()).getToken()
        viewModel.getUserInfo(token)
        viewModel.userInfoResponse.observe(viewLifecycleOwner) { userInfoData ->
            binding.userEmail.text = userInfoData.user.email
        }

        viewModel.language.observe(viewLifecycleOwner) {
            binding.tvSelectedLanguage.text = it
        }

        binding.language.setOnClickListener {
            val bottomSheet = SelectedLanguageBottomSheet()
            bottomSheet.setOnLanguageListener(this)
            bottomSheet.show(parentFragmentManager, bottomSheet.tag)
        }
    }

    override fun onLanguageSelected(language: String) {
        viewModel.selectLanguage(language)
    }

    private fun systemLanguage(){
        when(SharedProvider(requireContext()).getLanguage()) {
            "kk" -> {
                val locale = Locale("kk")
                Locale.setDefault(locale)
                val config = Configuration()
                config.setLocale(locale)
                requireContext().resources.updateConfiguration(config, requireContext().resources.displayMetrics)
                binding.tvSelectedLanguage.text = "Қазақша"
            }
            "ru" -> {
                val locale = Locale("ru")
                Locale.setDefault(locale)
                val config = Configuration()
                config.setLocale(locale)
                requireContext().resources.updateConfiguration(config, requireContext().resources.displayMetrics)
                binding.tvSelectedLanguage.text = "Русский"
            }
            "en" -> {
                val locale = Locale("en")
                Locale.setDefault(locale)
                val config = Configuration()
                config.setLocale(locale)
                requireContext().resources.updateConfiguration(config, requireContext().resources.displayMetrics)
                binding.tvSelectedLanguage.text = "English"
            }
            else -> {
                val locale = Locale("kk")
                Locale.setDefault(locale)
                val config = Configuration()
                config.setLocale(locale)
                requireContext().resources.updateConfiguration(config, requireContext().resources.displayMetrics)
                binding.tvSelectedLanguage.text = "Қазақша"
            }
        }
    }
}