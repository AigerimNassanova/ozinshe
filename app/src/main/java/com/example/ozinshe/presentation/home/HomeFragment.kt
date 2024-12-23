package com.example.ozinshe.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ozinshe.R
import com.example.ozinshe.data.SharedProvider
import com.example.ozinshe.databinding.FragmentHomeBinding
import com.example.ozinshe.domain.utils.provideNavigationHost
import com.example.ozinshe.presentation.home.adapter.CartoonsItemAdapter
import com.example.ozinshe.presentation.home.adapter.MainMoviesItemAdapter
import com.example.ozinshe.presentation.home.adapter.TvSeriesItemAdapter
import com.example.ozinshe.presentation.home.adapter.AnimatedSeriesItemAdapter
import com.example.ozinshe.presentation.home.adapter.SitcomItemAdapter
import com.example.ozinshe.presentation.home.interfaces.RcViewItemClickMainMoviesCallback

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

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
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        provideNavigationHost()?.apply {
            setNavigationVisibility(true)
        }

        binding.logoImg.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
        }

        val token = SharedProvider(requireContext()).getToken()
        viewModel.getMainMovies(token)
        val adapterMainMovies = MainMoviesItemAdapter()
        binding.mainMoviesRc.adapter = adapterMainMovies
        viewModel.mainMoviesResponse.observe(viewLifecycleOwner){
            adapterMainMovies.submitList(it)
        }

        adapterMainMovies.setOnMovieClickListener(object : RcViewItemClickMainMoviesCallback {
            override fun onclick(moviesId: Int) {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(moviesId)
                findNavController().navigate(action)
            }
        })

        viewModel.getMoviesByCategory(token)
        /*val adapterTvSeries = TvSeriesItemAdapter()
        binding.tvSeriesRc.adapter = adapterTvSeries*/

        val adapterCartoons = CartoonsItemAdapter()
        binding.cartoonsRc.adapter = adapterCartoons

        val adapterAnimatedSeries = AnimatedSeriesItemAdapter()
        binding.animatedSeriesRc.adapter = adapterAnimatedSeries

        val adapterSitcoms = SitcomItemAdapter()
        binding.sitcomsRc.adapter = adapterSitcoms


        viewModel.moviesByCategoryResponse.observe(viewLifecycleOwner){
            /*binding.tvSeriesBlockName.text = it[0].categoryName
            adapterTvSeries.submitList(it[0].movies)
            adapterTvSeries.setOnMovieClickListener(object : RcViewItemClickMainMoviesCallback {
                override fun onclick(moviesId: Int) {
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(moviesId)
                    findNavController().navigate(action)
                }
            })*/

            binding.cartoonsBlockName.text = it[0].categoryName
            adapterCartoons.submitList(it[0].movies)
            Log.d("BBB", "${it[0].categoryName} - ${it[0].movies}")
            adapterCartoons.setOnMovieClickListener(object : RcViewItemClickMainMoviesCallback {
                override fun onclick(moviesId: Int) {
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(moviesId)
                    findNavController().navigate(action)
                }
            })

            binding.animatedSeriesBlockName.text = it[1].categoryName
            adapterAnimatedSeries.submitList(it[1].movies)
            adapterAnimatedSeries.setOnMovieClickListener(object :
                RcViewItemClickMainMoviesCallback {
                override fun onclick(moviesId: Int) {
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(moviesId)
                    findNavController().navigate(action)
                }
            })

            binding.sitcomsBlockName.text = it[2].categoryName
            adapterSitcoms.submitList(it[2].movies)
            adapterSitcoms.setOnMovieClickListener(object : RcViewItemClickMainMoviesCallback {
                override fun onclick(moviesId: Int) {
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(moviesId)
                    findNavController().navigate(action)
                }
            })
        }
    }
}
