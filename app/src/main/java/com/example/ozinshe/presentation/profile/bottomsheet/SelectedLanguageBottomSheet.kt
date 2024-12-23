package com.example.ozinshe.presentation.profile.bottomsheet

import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.ozinshe.R
import com.example.ozinshe.data.SharedProvider
import com.example.ozinshe.databinding.BottomsheetSelectLanguageBinding
import com.example.ozinshe.presentation.profile.OnLanguageSelectedListener
import com.example.ozinshe.presentation.profile.ProfileViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Locale

class SelectedLanguageBottomSheet: BottomSheetDialogFragment() {

    private lateinit var binding: BottomsheetSelectLanguageBinding

    private var languageSelectedListener: OnLanguageSelectedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomsheetSelectLanguageBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun setOnLanguageListener(listener: OnLanguageSelectedListener){
        languageSelectedListener = listener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        when(SharedProvider(requireContext()).getLanguage()) {
            "kk" -> {
                selectedIcon(false, true, false)
            }
            "en" -> {
                selectedIcon(true, false, false)
            }
            "ru" -> {
                selectedIcon(false, false, true)
            }
        }

        binding.apply {
            btnSelectEnglish.setOnClickListener {
                selectedIcon(true, false, false)
                changeLanguage("English")

            }
            btnSelectKazakh.setOnClickListener {
                selectedIcon(false, true, false)
                changeLanguage("Қазақша")
            }
            btnSelectRussian.setOnClickListener {
                selectedIcon(false, false, true)
                changeLanguage("Русский")
            }
        }
    }

    fun changeLanguage(language: String){
        when(language) {
            "English" -> {
                systemLanguageChange("en")
                languageSelectedListener?.onLanguageSelected("English")
            }
            "Қазақша" -> {
                systemLanguageChange("kk")
                languageSelectedListener?.onLanguageSelected("Қазақша")
            }
            "Русский" -> {
                systemLanguageChange("ru")
                languageSelectedListener?.onLanguageSelected("Русский")
            }
        }
    }

    fun systemLanguageChange(codeLanguage: String){
        SharedProvider(requireContext()).saveLanguage(codeLanguage)
        val locale = Locale(codeLanguage)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        requireContext().resources.updateConfiguration(config, requireContext().resources.displayMetrics)

        findNavController().navigate(R.id.profileFragment, arguments, NavOptions.Builder().setPopUpTo(R.id.profileFragment, true).build())
    }

    fun selectedIcon(iconEnglish: Boolean, iconKazakh: Boolean, iconRussian: Boolean){
        if (iconEnglish) {
            binding.imgIconBtnEnglishSelect.visibility = View.VISIBLE
        } else {
            binding.imgIconBtnEnglishSelect.visibility = View.GONE
        }

        if (iconKazakh) {
            binding.imgIconBtnKazakhSelect.visibility = View.VISIBLE
        } else {
            binding.imgIconBtnKazakhSelect.visibility = View.GONE
        }

        if (iconRussian) {
            binding.imgIconBtnRussianSelect.visibility = View.VISIBLE
        } else {
            binding.imgIconBtnRussianSelect.visibility = View.GONE
        }
    }
}