package com.example.ozinshe.presentation.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ozinshe.databinding.AdapterRcSeriesItemBinding
import com.example.ozinshe.domain.model.SeriesListResponseItem
import com.example.ozinshe.domain.model.VideoXXX
import com.example.ozinshe.presentation.home.interfaces.RcViewItemClickVideoCallback

class SeriesItemAdapter(): RecyclerView.Adapter<SeriesItemAdapter.SeriesViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<VideoXXX>(){
        override fun areItemsTheSame(
            oldItem: VideoXXX,
            newItem: VideoXXX
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: VideoXXX,
            newItem: VideoXXX
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    fun submitList(listVideos: List<VideoXXX>){
        differ.submitList(listVideos)
    }

    private var listenerClickAtItem: RcViewItemClickVideoCallback? = null

    fun setOnVideoClickListener(listener: RcViewItemClickVideoCallback){
        this.listenerClickAtItem = listener
    }

    inner class SeriesViewHolder(private val binding: AdapterRcSeriesItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindItem(video: VideoXXX) {
            binding.apply {
                Log.d("III", "http://img.youtube.com/vi/${video.link}/maxresdefault.jpg")
                Glide.with(itemView)
                    .load("http://img.youtube.com/vi/${video.link}/maxresdefault.jpg")
                    .centerCrop()
                    .into(binding.seriesImg)
                seriesTitle.text = "${video.number}-ші бөлім"
                root.setOnClickListener{
                    listenerClickAtItem?.onSeriesItemClick(video.link)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val binding = AdapterRcSeriesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeriesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        holder.bindItem(differ.currentList[position])
    }

}