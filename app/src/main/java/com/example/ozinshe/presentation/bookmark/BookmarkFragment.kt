package com.example.ozinshe.presentation.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ozinshe.R
import com.example.ozinshe.data.SharedProvider
import com.example.ozinshe.databinding.FragmentBookmarkBinding
import com.example.ozinshe.domain.utils.provideNavigationHost
import com.example.ozinshe.presentation.bookmark.adapter.FavouriteItemAdapter
import com.example.ozinshe.presentation.home.HomeFragmentDirections
import com.example.ozinshe.presentation.home.interfaces.RcViewItemClickMainMoviesCallback
import com.example.ozinshe.presentation.home.series.CustomDividerItemDecoration

class BookmarkFragment : Fragment() {

    private lateinit var binding: FragmentBookmarkBinding

    private val viewModel: BookmarkViewModel by viewModels()

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
        binding = FragmentBookmarkBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        provideNavigationHost()?.apply {
            setNavigationVisibility(true)
        }

        val token = SharedProvider(requireContext()).getToken()
        viewModel.getFavourite(token)
        val adapterFavourite = FavouriteItemAdapter()
        binding.favouritesRc.adapter = adapterFavourite
        viewModel.favouriteMoviesResponse.observe(viewLifecycleOwner) {
            adapterFavourite.submitList(it)
        }

        binding.favouritesRc.addItemDecoration(
            CustomDividerItemDecoration(
                ContextCompat.getDrawable(requireContext(), R.drawable.divider_1dp_drey)!!
            )
        )

        adapterFavourite.setOnMovieClickListener(object : RcViewItemClickMainMoviesCallback {
            override fun onclick(moviesId: Int) {
                val action = BookmarkFragmentDirections.actionBookmarkFragmentToDetailFragment(moviesId)
                findNavController().navigate(action)
            }
        })
    }

}