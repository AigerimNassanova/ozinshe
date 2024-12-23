package com.example.ozinshe.presentation.sign_up

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ozinshe.R
import com.example.ozinshe.databinding.FragmentRegistrationBinding
import com.example.ozinshe.domain.utils.provideNavigationHost

class RegistrationFragment : Fragment() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var repeatPasswordEditText: EditText
    private lateinit var signUpButton: Button
    private lateinit var errorTextEmail: TextView
    private lateinit var errorTextPassword1: TextView
    private lateinit var errorTextPassword2: TextView

    private lateinit var binding: FragmentRegistrationBinding

    private val viewModel: RegistrationViewModel by viewModels()

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
        binding = FragmentRegistrationBinding.inflate(layoutInflater, container, false)
        binding.toolbarLog.backBtn.setOnClickListener() {
            findNavController().popBackStack()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.registrationResponse.observe(viewLifecycleOwner) {
            errorTextEmail.visibility = View.GONE
            errorTextPassword1.visibility = View.GONE
            errorTextPassword2.visibility = View.GONE
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.homeFragment)
        }
        viewModel.errorResponse.observe(viewLifecycleOwner){
            showError(it)
        }
        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
        }
        emailEditText = binding.rgEmailEditText
        passwordEditText = binding.rgPasswordEditText
        repeatPasswordEditText = binding.rgPassword2EditText
        signUpButton =binding.signUpBtn
        errorTextEmail = binding.tvErrorTextEmail
        errorTextPassword1 = binding.tvErrorTextPassword1
        errorTextPassword2 = binding.tvErrorTextPassword2
        signUpButton.setOnClickListener {
            validateAndRegister()
        }
        btnCheck()
        showPassword()
    }

    private fun showPassword() {
        binding.btnShowPasswordAgain.setOnClickListener {

            if (binding.rgPasswordEditText.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
                binding.rgPasswordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.btnShowPasswordAgain.setImageResource(R.drawable.ic_eye_pass_registration)
            } else {
                binding.rgPasswordEditText.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                binding.btnShowPasswordAgain.setImageResource(R.drawable.ic_eye_off)
            }
        }

        binding.btnShowPassword2Again.setOnClickListener {

            if (binding.rgPassword2EditText.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
                binding.rgPassword2EditText.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.btnShowPassword2Again.setImageResource(R.drawable.ic_eye_pass_registration)
            } else {
                binding.rgPassword2EditText.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                binding.btnShowPassword2Again.setImageResource(R.drawable.ic_eye_off)
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
            binding.rgEmailEditText.setBackgroundResource(R.drawable.edit_text_border)
        } else {
            binding.tvErrorTextEmail.visibility = View.VISIBLE
            binding.rgEmailEditText.setBackgroundResource(R.drawable.error_edit_text_border)
        }
    }

    private fun validationPassword(password: String): Boolean {
        return if (password.length < 6){
            binding.tvErrorTextPassword1.visibility = View.VISIBLE
            binding.rgPasswordEditText.setBackgroundResource(R.drawable.error_edit_text_border)
            false
        } else {
            binding.tvErrorTextPassword1.visibility = View.GONE
            binding.rgPasswordEditText.setBackgroundResource(R.drawable.edit_text_border)
            true
        }
    }

    private fun checkMatchingPasswords(password: String, repeatPassword: String): Boolean{
        return if (password != repeatPassword) {
            errorTextPassword2.text = "Құпия сөздер сәйкес емес"
            errorTextPassword2.visibility = View.VISIBLE
            false
        } else {
            errorTextPassword2.visibility = View.GONE
            true
        }
    }

    private fun validateAndRegister() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()
        val repeatPassword = repeatPasswordEditText.text.toString().trim()

        errorTextEmail.visibility = View.GONE
        errorTextPassword1.visibility = View.GONE
        errorTextPassword2.visibility = View.GONE

        val emailIsValid = emailValidation(email)

        val passwordIsValid = validationPassword(password)

        val passwordsAreMatched = checkMatchingPasswords(password, repeatPassword)

        if (emailIsValid && passwordIsValid && passwordsAreMatched) {
            viewModel.registrationUser(email, password)
        } else {
            validationEmail(emailIsValid)
        }
    }

    private fun showError(message: String) {
        binding.tvErrorTextPassword2.text = message
        binding.tvErrorTextPassword2.visibility = View.VISIBLE
    }

    private fun btnCheck() {
        binding?.apply {
            btnTextTransitionForRegistration.setOnClickListener {
                findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
            }
        }
    }
}