package com.example.ozinshe.presentation.home.detail

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.ozinshe.R
import com.example.ozinshe.data.SharedProvider
import com.example.ozinshe.databinding.FragmentDetailBinding
import com.example.ozinshe.domain.model.MovieIdModel
import com.example.ozinshe.domain.utils.provideNavigationHost
import com.example.ozinshe.presentation.home.interfaces.RcViewItemClickImageCallback
import com.example.ozinshe.presentation.home.adapter.ScreenItemAdapter

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()

    private lateinit var descriptionTextView: TextView
    private lateinit var fadeImageView: ImageView
    private lateinit var showMoreButton: TextView
    private var isTextExpanded = false

    private var favouriteState: Boolean = false

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
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        provideNavigationHost()?.apply {
            setNavigationVisibility(true)
        }
        binding.seriesTitle.visibility = View.GONE
        binding.goToSeriesBlock.visibility = View.GONE
        binding.toolbarLog.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        Toast.makeText(requireContext(), "${args.movieId}", Toast.LENGTH_SHORT).show()

        val token = SharedProvider(requireContext()).getToken()
        viewModel.getMovieById(token, args.movieId)

        viewModel.moviesByIdResponse.observe(viewLifecycleOwner){
            if (it.video == null){
                binding.seriesTitle.visibility = View.VISIBLE
                binding.seriesTitle.setOnClickListener {
                    val action = DetailFragmentDirections.actionDetailFragmentToSeriesFragment(args.movieId)
                    findNavController().navigate(action)
                }
                binding.goToSeriesBlock.visibility = View.VISIBLE
                binding.goToSeriesBlock.setOnClickListener {
                    val action = DetailFragmentDirections.actionDetailFragmentToSeriesFragment(args.movieId)
                    findNavController().navigate(action)
                }
                binding.totalSeries.text = "${it.seasonCount} сезон, ${it.seriesCount} серия"
            } else {
                binding.seriesTitle.visibility = View.GONE
                binding.goToSeriesBlock.visibility = View.GONE
            }
            binding.playImg.setOnClickListener {click ->
                if(it.video != null) {
                    val action = DetailFragmentDirections.actionDetailFragmentToPlayerFragment(
                        it.video.link)
                    findNavController().navigate(action)
                } else {
                    val action = DetailFragmentDirections.actionDetailFragmentToSeriesFragment(args.movieId)
                    findNavController().navigate(action)
                }
            }

            if (it.favorite) {
                favouriteState = true
                binding.addToListImg.setImageResource(R.drawable.ic_bookmark_filled)
            } else {
                favouriteState = false
                binding.addToListImg.setImageResource(R.drawable.ic_bookmark_outline)
            }

            Glide.with(requireContext()).load(it.poster.link).into(binding.filmImg)
            binding.filmTitle.text = it.name
            binding.filmYear.text = it.year.toString()
            var additionalInfo = ""
            for (i in it.genres) {
                additionalInfo += "${i.name} "
            }
            binding.filmGenre.text = additionalInfo
            binding.filmDuration.text = it.timing.toString()
            binding.mainRecDescription.text = it.description
            binding.directorName.text = it.director
            binding.producerName.text = it.producer

            val adapterScreenshots = ScreenItemAdapter()
            binding.screenRc.adapter = adapterScreenshots
            adapterScreenshots.submitList(it.screenshots)
            adapterScreenshots.setOnImageClickListener(object : RcViewItemClickImageCallback {
                override fun onClick(link: String) {
                    val action = DetailFragmentDirections.actionDetailFragmentToImageFragment(link)
                    findNavController().navigate(action)
                }

            })

            binding.addToListImg.setOnClickListener { click ->
                if (!favouriteState) {
                    viewModel.addFavourite(token, MovieIdModel(args.movieId))
                } else {
                    viewModel.deleteFavourite(token, MovieIdModel(args.movieId))
                }
            }
        }

        viewModel.favouriteState.observe(viewLifecycleOwner) {
            if (it) {
                favouriteState = true
                binding.addToListImg.setImageResource(R.drawable.ic_bookmark_filled)
            } else {
                favouriteState = false
                binding.addToListImg.setImageResource(R.drawable.ic_bookmark_outline)
            }
        }

        viewModel.errorResponse.observe(viewLifecycleOwner){
            Log.d("AAA", it)
        }

        descriptionTextView = binding.mainRecDescription
        fadeImageView = binding.fadeImg
        showMoreButton = binding.showMoreTv

        if (descriptionTextView.lineCount == 1) {
            showMoreButton.visibility = View.GONE
        } else {
            showMoreButton.visibility = View.VISIBLE
            showMoreButton.setOnClickListener {
                if (isTextExpanded) {
                    // Collapse the text
                    descriptionTextView.maxLines = 7
                    descriptionTextView.ellipsize = android.text.TextUtils.TruncateAt.END

                    // Fade in the gradient
                    val fadeIn = ObjectAnimator.ofFloat(fadeImageView, "alpha", 0f, 1f)
                    fadeIn.duration = 300
                    fadeIn.start()

                    // Update the button text to "Show More"
                    showMoreButton.text = "Толығырақ"
                } else {
                    // Expand the text
                    descriptionTextView.maxLines = Integer.MAX_VALUE
                    descriptionTextView.ellipsize = null

                    // Fade out the gradient
                    val fadeOut = ObjectAnimator.ofFloat(fadeImageView, "alpha", 1f, 0f)
                    fadeOut.duration = 300
                    fadeOut.start()

                    // Update the button text to "Hide"
                    showMoreButton.text = "Жасыру"
                }

                // Toggle the state
                isTextExpanded = !isTextExpanded
            }
        }
    }
}

