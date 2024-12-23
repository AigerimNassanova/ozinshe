package com.example.ozinshe.presentation.profile.editProfile

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ozinshe.R
import com.example.ozinshe.data.SharedProvider
import com.example.ozinshe.databinding.FragmentEditProfileBinding
import com.example.ozinshe.domain.model.UserInfoRequest
import com.example.ozinshe.domain.utils.provideNavigationHost
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class EditProfileFragment : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding

    private val viewModel: EditProfileViewModel by viewModels()

    private val calendar = Calendar.getInstance()

    private var id = 0

    private var afterUserName = ""
    private var afterUserNumber = ""
    private var afterUserEmail = ""
    private var afterUserDateOfBirth = ""
    private var afterLanguage = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textInputEditTextName.inputType = InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
        }

        binding.toolbarLog.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.textInputEditTextDateOfBirth.setOnTouchListener { v, event ->
            val DRAWABLE_LEFT = 0
            val DRAWABLE_TOP = 1
            val DRAWABLE_RIGHT = 2
            val DRAWABLE_BOTTOM = 3

            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (binding.textInputEditTextDateOfBirth.right - binding.textInputEditTextDateOfBirth.compoundDrawables[DRAWABLE_RIGHT].bounds.width())) {
                    showDatePicker()
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }

        val token = SharedProvider(requireContext()).getToken()
        viewModel.getUserInfo(token)
        viewModel.userInfoResponse.observe(viewLifecycleOwner) { userInfoData ->
            id = userInfoData.id
            binding.textInputEditTextName.setText(userInfoData.name)
            binding.textInputEditTextPhoneNumber.setText(userInfoData.phoneNumber)
            binding.textInputEditTextEmail.setText(userInfoData.user.email)
            binding.textInputEditTextDateOfBirth.setText(userInfoData.birthDate)
            saveChangesMyData()
        }

        observerErrorResponse()
        observerUpdateUserInfo()

        binding.btnSaveUserDataUpdates.setOnClickListener {
            Log.d("AAA", "Btn was clicked")
            val requestBody = autoObjectRequest()
            updateUserInfoData(token, requestBody)
        }

    }

    private fun autoObjectRequest(): UserInfoRequest {
        var userName = ""
        var userNumber = ""
        var userEmail = ""
        var userDateOfBirth = ""
        var language = ""

        binding.apply {
            userName = textInputEditTextName.text.toString()
            userNumber = textInputEditTextPhoneNumber.text.toString()
            userEmail = textInputEditTextEmail.text.toString()
            userDateOfBirth = textInputEditTextDateOfBirth.text.toString()


            afterUserName = userName
            afterUserNumber = userNumber
            afterUserEmail = userEmail
            afterUserDateOfBirth = userDateOfBirth
        }
        return UserInfoRequest(
            userDateOfBirth,
            id,
            language,
            userName,
            userNumber
        )
    }


    private fun updateUserInfoData(token: String, requestBody: UserInfoRequest) {
        viewModel.updateUserInfo(token, requestBody)
    }

    private fun observerUpdateUserInfo() {
        viewModel.postUserInfoResponse.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Данные сохранены", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

    private fun observerErrorResponse() {
        viewModel.errorResponse.observe(viewLifecycleOwner) {
            Log.d("AAA", " Error at VM : $it")
        }
    }

    private fun editTextChangesListener(changedData: TextInputEditText) {
        changedData.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                updateButtonBackground()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val text = s?.toString()?.trim() // Trimmed to remove leading and trailing whitespaces

                if (text.isNullOrEmpty()) {
                    binding.textInputLayoutName.errorIconDrawable?.setTint(Color.RED) // Set error icon color to red
                }
            }
        })
    }

    private fun updateButtonBackground() {
        val isAllDataFilled = binding.textInputEditTextName?.text?.isNotEmpty() ?: false &&
                binding.textInputEditTextPhoneNumber?.text?.isNotEmpty() ?: false  &&
                binding.textInputEditTextEmail?.text?.isNotEmpty() ?: false  &&
                binding.textInputEditTextDateOfBirth?.text?.isNotEmpty() ?: false

        if (isAllDataFilled){
            binding.btnSaveUserDataUpdates.setBackgroundResource(R.drawable.btn_save_changes)
            binding.btnSaveUserDataUpdates.isEnabled = true
        } else {
            // If any field is empty, keep the button disabled
            binding.btnSaveUserDataUpdates.setBackgroundResource(R.drawable.btn_save_changes_default)
            binding.btnSaveUserDataUpdates.isEnabled = false
        }
    }

    private fun saveChangesMyData() {
        editTextChangesListener(binding.textInputEditTextName)
        editTextChangesListener(binding.textInputEditTextPhoneNumber)
        editTextChangesListener(binding.textInputEditTextEmail)
        editTextChangesListener(binding.textInputEditTextDateOfBirth)
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(
            requireContext(), { DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale("ru", "RU"))
                val formattedDate: String = dateFormat.format(selectedDate.time)
                binding.textInputEditTextDateOfBirth.setText(formattedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

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

}