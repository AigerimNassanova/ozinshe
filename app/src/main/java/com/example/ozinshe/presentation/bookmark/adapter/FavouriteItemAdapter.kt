package com.example.ozinshe.presentation.bookmark.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ozinshe.databinding.AdapterRcCategoryItemBinding
import com.example.ozinshe.domain.model.FavoriteMoviesResponse
import com.example.ozinshe.domain.model.FavoriteMoviesResponseItem
import com.example.ozinshe.domain.model.MainMoviesResponseItem
import com.example.ozinshe.presentation.home.interfaces.RcViewItemClickMainMoviesCallback

class FavouriteItemAdapter(): RecyclerView.Adapter<FavouriteItemAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<FavoriteMoviesResponseItem>(){
        override fun areItemsTheSame(
            oldItem: FavoriteMoviesResponseItem,
            newItem: FavoriteMoviesResponseItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: FavoriteMoviesResponseItem,
            newItem: FavoriteMoviesResponseItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(listMainMovies: List<FavoriteMoviesResponseItem>){
        differ.submitList(listMainMovies)
    }

    private var listenerClickAtItem: RcViewItemClickMainMoviesCallback? = null

    fun setOnMovieClickListener(listener: RcViewItemClickMainMoviesCallback){
        this.listenerClickAtItem = listener
    }

    inner class ViewHolder(private val binding: AdapterRcCategoryItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindItem(favoriteMoviesItem: FavoriteMoviesResponseItem){
            Glide.with(itemView.context)
                .load(favoriteMoviesItem.poster.link)
                .into(binding.filmImg)

            binding.filmTitle.text = favoriteMoviesItem.name
            binding.filmYear.text = favoriteMoviesItem.year.toString()
            var additionalInfo = ""
            for (i in favoriteMoviesItem.genres) {
                additionalInfo += "${i.name} "
            }
            binding.filmgenre.text = additionalInfo
            itemView.setOnClickListener {
                listenerClickAtItem?.onclick(favoriteMoviesItem.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterRcCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(differ.currentList[position])
    }
}