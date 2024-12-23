package com.example.ozinshe.presentation.sign_in

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ozinshe.R
import com.example.ozinshe.data.SharedProvider
import com.example.ozinshe.databinding.FragmentLoginBinding
import com.example.ozinshe.domain.utils.provideNavigationHost

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()

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
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        binding.toolbarLog.backBtn.setOnClickListener() {
            findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loginResponse.observe(viewLifecycleOwner) {
            binding.tvErrorTextPassword.visibility = View.GONE
            binding.tvErrorTextEmail.visibility = View.GONE
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
            SharedProvider(requireContext()).saveToken(it.accessToken)
            findNavController().navigate(R.id.homeFragment)
        }
        viewModel.errorResponse.observe(viewLifecycleOwner){
            showError(it)
        }
        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
        }
        binding.signInBtn.isEnabled = true
        btnCheck()
        showPassword()
        binding.signInBtn.setOnClickListener {
            val email = binding.lgEmailEditText.text.toString()
            val password = binding.lgPasswordEditText.text.toString()

            val emailIsValid = emailValidation(email)
            val passwordIsValid = validationPassword(password)

            if (emailIsValid && passwordIsValid) {
                viewModel.loginUser(email, password)
            } else {
                validationEmail(emailIsValid)
            }
        }
    }

    private fun showPassword() {
        binding.btnShowPasswordAgain.setOnClickListener {

            if (binding.lgPasswordEditText.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
                binding.lgPasswordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.btnShowPasswordAgain.setImageResource(R.drawable.ic_eye_pass_registration)
            } else {
                binding.lgPasswordEditText.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                binding.btnShowPasswordAgain.setImageResource(R.drawable.ic_eye_off)
            }
        }
    }

    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private fun emailValidation(email: String): Boolean {
        return email.trim().matches(emailPattern.toRegex())
    }

    private fun validationEmail(isValid: Boolean){
        if (isValid){
            binding.tvErrorTextEmail.visibility = View.GONE
            binding.lgEmailEditText.setBackgroundResource(R.drawable.edit_text_border)
        } else {
            binding.tvErrorTextEmail.visibility = View.VISIBLE
            binding.lgEmailEditText.setBackgroundResource(R.drawable.error_edit_text_border)
        }
    }

    private fun validationPassword(password: String): Boolean {
        return if (password.length < 6){
            binding.tvErrorTextPassword.text = "Құпия сөздің ұзындығы 6 таңбадан кем емес"
            binding.tvErrorTextPassword.visibility = View.VISIBLE
            binding.lgEmailEditText.setBackgroundResource(R.drawable.error_edit_text_border)
            false
        } else {
            binding.tvErrorTextPassword.visibility = View.GONE
            binding.lgEmailEditText.setBackgroundResource(R.drawable.edit_text_border)
            true
        }
    }

    private fun btnCheck() {
        binding?.apply {
            btnTextTransitionForLogIn.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
            }
        }
    }

    private fun showError(message: String) {
        binding.tvErrorTextPassword.text = message
        binding.tvErrorTextPassword.visibility = View.VISIBLE
    }

}