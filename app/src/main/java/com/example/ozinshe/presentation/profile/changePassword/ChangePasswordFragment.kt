package com.example.ozinshe.presentation.profile.changePassword

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ozinshe.R
import com.example.ozinshe.data.SharedProvider
import com.example.ozinshe.databinding.FragmentChangePasswordBinding
import com.example.ozinshe.domain.utils.provideNavigationHost

class ChangePasswordFragment : Fragment() {

    private lateinit var binding: FragmentChangePasswordBinding

    private val viewModel: ChangePasswordViewModel by viewModels()

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
        binding = FragmentChangePasswordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showPassword()
        provideNavigationHost()?.apply {
            setNavigationVisibility(true)
        }
        binding.toolbarLog.backBtn.setOnClickListener {
            findNavController().navigate(R.id.profileFragment)
        }

        editTextChangesListener(binding.passwordEditText)
        editTextChangesListener(binding.repeatPasswordEditText)

        binding.btnSavePassword.setOnClickListener {
            val password = binding.passwordEditText.text.toString()
            val repeatPassword = binding.repeatPasswordEditText.text.toString()

            if (password == repeatPassword) {
                viewModel.resetPassword(
                    SharedProvider(requireContext()).getToken(),
                    binding.passwordEditText.text.toString()
                )
                Toast.makeText(requireContext(), "Passwords match!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Passwords do not match. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnSavePassword.isEnabled = false

        viewModel.errorResponse.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "При смене пароля произошла ошибка, текущий пароль может быть неверным", Toast.LENGTH_SHORT).show()
        }
        viewModel.responseSuccess.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun editTextChangesListener(changedData: EditText) {
        changedData.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                updateButtonState()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val text = s?.toString()?.trim() // Trimmed to remove leading and trailing whitespaces

                if (text.isNullOrEmpty()) {
                    changedData.setBackgroundColor(Color.RED) // Set error icon color to red
                }
            }
        })
    }

    private fun updateButtonState() {
        val isAllDataFilled =
            binding.passwordEditText?.text?.isNotEmpty() ?: false &&
                    binding.repeatPasswordEditText?.text?.isNotEmpty() ?: false &&
                    (binding.passwordEditText?.text?.length ?: 0) >= 6 &&
                    (binding.repeatPasswordEditText?.text?.length ?: 0) >= 6

        if (isAllDataFilled) {
            binding.btnSavePassword.setBackgroundResource(R.drawable.btn_save_changes)
            binding.btnSavePassword.isEnabled = true
        } else {
            binding.btnSavePassword.setBackgroundResource(R.drawable.btn_save_changes_default)
            binding.btnSavePassword.isEnabled = false
        }
    }

    private fun showPassword() {
        binding.btnShowPasswordAgain.setOnClickListener {
            if (binding.passwordEditText.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
                binding.passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.btnShowPasswordAgain.setImageResource(R.drawable.ic_eye_pass_registration)
            } else {
                binding.passwordEditText.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                binding.btnShowPasswordAgain.setImageResource(R.drawable.ic_eye_off)
            }
        }
        binding.btnShowRepeatPasswordAgain.setOnClickListener {
            if (binding.repeatPasswordEditText.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
                binding.repeatPasswordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.btnShowRepeatPasswordAgain.setImageResource(R.drawable.ic_eye_pass_registration)
            } else {
                binding.repeatPasswordEditText.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                binding.btnShowRepeatPasswordAgain.setImageResource(R.drawable.ic_eye_off)
            }
        }
    }
}