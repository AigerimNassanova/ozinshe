package com.example.ozinshe.presentation.home.series

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getDrawable
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ozinshe.R
import com.example.ozinshe.data.SharedProvider
import com.example.ozinshe.databinding.FragmentSeriesBinding
import com.example.ozinshe.domain.utils.provideNavigationHost
import com.example.ozinshe.presentation.home.adapter.SeriesItemAdapter
import com.example.ozinshe.presentation.home.interfaces.RcViewItemClickVideoCallback

class SeriesFragment : Fragment() {

    private lateinit var binding: FragmentSeriesBinding

    private val viewModel: SeriesViewModel by viewModels()

    private val args: SeriesFragmentArgs by navArgs()

    override fun onStart() {
        super.onStart()
        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeriesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
        }

        binding.toolbarLog.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.seasonNumberRc.isFocused

        val token = SharedProvider(requireContext()).getToken()
        viewModel.getSeries(token, args.movieId)
        val adapterSeries = SeriesItemAdapter()
        binding.seriesRc.adapter = adapterSeries
        viewModel.seriesResponse.observe(viewLifecycleOwner) {
            adapterSeries.submitList(it[0].videos)
        }
        binding.seriesRc.addItemDecoration(
            CustomDividerItemDecoration(
                getDrawable(requireContext(), R.drawable.divider_1dp_drey)!!
            )
        )
        adapterSeries.setOnVideoClickListener(object : RcViewItemClickVideoCallback {
            override fun onSeriesItemClick(seriesLink: String) {
                val action = SeriesFragmentDirections.actionSeriesFragmentToPlayerFragment(seriesLink)
                findNavController().navigate(action)
            }

        })
    }
}