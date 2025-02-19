package com.example.ozinshe.presentation.home.player

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.setPadding
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ozinshe.R
import com.example.ozinshe.databinding.FragmentPlayerBinding
import com.example.ozinshe.domain.utils.provideNavigationHost
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController
import kotlinx.coroutines.launch

class PlayerFragment : Fragment() {

    private lateinit var binding: FragmentPlayerBinding

    private var currentTime: Float? = null

    private val args: PlayerFragmentArgs by navArgs()

    override fun onResume() {
        super.onResume()
        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
        }
        requireActivity().requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    override fun onStart() {
        super.onStart()
        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
        }
        requireActivity().requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
        }
        requireActivity().requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        lifecycleScope.launch {
            playerYouTube(args.videoLink)
        }
    }

    private fun playerYouTube(movieLink: String) {
        val youTubePlayerView = binding.youtubePlayerView

        lifecycle.addObserver(youTubePlayerView!!)
        val listener: YouTubePlayerListener = object : AbstractYouTubePlayerListener() {
            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                super.onCurrentSecond(youTubePlayer, second)
                currentTime = second
            }

            override fun onReady(youTubePlayer: YouTubePlayer) {
                // using pre-made custom ui
                val defaultPlayerUiController = DefaultPlayerUiController(
                    youTubePlayerView!!, youTubePlayer
                )

                defaultPlayerUiController.rootView.findViewById<View>(com.pierfrancescosoffritti.androidyoutubeplayer.R.id.drop_shadow_top)
                    .apply {
                        setBackgroundResource(R.drawable.close_video)
                        setPadding(24)
                        updateLayoutParams {
                            width = 200
                            height = 200
                        }
                        setOnClickListener {
                            findNavController().popBackStack()
                        }
                    }

                youTubePlayerView!!.setCustomPlayerUi(defaultPlayerUiController.rootView)
                defaultPlayerUiController.showYouTubeButton(false)
                defaultPlayerUiController.showFullscreenButton(false)
                defaultPlayerUiController.rootView.findViewById<com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.views.YouTubePlayerSeekBar>(
                    com.pierfrancescosoffritti.androidyoutubeplayer.R.id.youtube_player_seekbar
                ).setColor(resources.getColor(R.color.violet_500, null))

                youTubePlayer.loadOrCueVideo(lifecycle, movieLink, 0f)
            }
        }

        // disable web ui
        val options: IFramePlayerOptions = IFramePlayerOptions.Builder().controls(0).build()

        youTubePlayerView!!.initialize(listener, options)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

}

