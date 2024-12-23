package com.example.ozinshe.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ozinshe.databinding.AdapterRcTrendsItemBinding
import com.example.ozinshe.domain.model.Movy
import com.example.ozinshe.presentation.home.interfaces.RcViewItemClickMainMoviesCallback

class SitcomItemAdapter(): RecyclerView.Adapter<SitcomItemAdapter.TvRealityViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Movy>(){
        override fun areItemsTheSame(
            oldItem: Movy,
            newItem: Movy
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Movy,
            newItem: Movy
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(listMainMovies: List<Movy>){
        differ.submitList(listMainMovies)
    }

    private var listenerClickAtItem: RcViewItemClickMainMoviesCallback? = null

    fun setOnMovieClickListener(listener: RcViewItemClickMainMoviesCallback){
        this.listenerClickAtItem = listener
    }

    inner class TvRealityViewHolder(private var binding: AdapterRcTrendsItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindItem(moviesByCategoryItem: Movy){
            Glide.with(itemView.context)
                .load(moviesByCategoryItem.poster.link)
                .into(binding.trendsImg)

            binding.trendsTitle.text = moviesByCategoryItem.name
            binding.trendsDescription.text = moviesByCategoryItem.categories[0].name
            itemView.setOnClickListener {
                listenerClickAtItem?.onclick(moviesByCategoryItem.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvRealityViewHolder {
        return TvRealityViewHolder(
            AdapterRcTrendsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: TvRealityViewHolder, position: Int) {
        holder.bindItem(differ.currentList[position])
    }
}