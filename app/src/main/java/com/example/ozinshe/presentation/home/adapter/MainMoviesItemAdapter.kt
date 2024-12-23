package com.example.ozinshe.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ozinshe.databinding.AdapterRcMainMoviesItemBinding
import com.example.ozinshe.domain.model.MainMoviesResponseItem
import com.example.ozinshe.presentation.home.interfaces.RcViewItemClickMainMoviesCallback

class MainMoviesItemAdapter(): RecyclerView.Adapter<MainMoviesItemAdapter.MainMoviesHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<MainMoviesResponseItem>(){
        override fun areItemsTheSame(
            oldItem: MainMoviesResponseItem,
            newItem: MainMoviesResponseItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: MainMoviesResponseItem,
            newItem: MainMoviesResponseItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(listMainMovies: List<MainMoviesResponseItem>){
        differ.submitList(listMainMovies)
    }

    private var listenerClickAtItem: RcViewItemClickMainMoviesCallback? = null

    fun setOnMovieClickListener(listener: RcViewItemClickMainMoviesCallback){
        this.listenerClickAtItem = listener
    }

    inner class MainMoviesHolder(private var binding: AdapterRcMainMoviesItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindItem(mainMoviesItem: MainMoviesResponseItem){
            Glide.with(itemView.context)
                .load(mainMoviesItem.link)
                .into(binding.mainMoviesImg)

            binding.mainMoviesTitle.text = mainMoviesItem.movie.name
            binding.mainMoviesDescription.text = mainMoviesItem.movie.description
            itemView.setOnClickListener {
                listenerClickAtItem?.onclick(mainMoviesItem.movie.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMoviesHolder {
        return MainMoviesHolder(
            AdapterRcMainMoviesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MainMoviesHolder, position: Int) {
        holder.bindItem(differ.currentList[position])
    }
}
