package com.example.ozinshe.presentation.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ozinshe.R
import com.example.ozinshe.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editText = binding.searchEditText
        val cancelButton = binding.cancelButton

        // Show the cancel button when the user starts typing
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    cancelButton.visibility = View.GONE
                    binding.searchBtn.setBackgroundResource(R.drawable.ic_search_on)
                } else {
                    cancelButton.visibility = View.VISIBLE
                    binding.searchBtn.setBackgroundResource(R.drawable.ic_search_main)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Clear the text when the cancel button is clicked
        cancelButton.setOnClickListener {
            editText.text.clear()
        }

    }
}

