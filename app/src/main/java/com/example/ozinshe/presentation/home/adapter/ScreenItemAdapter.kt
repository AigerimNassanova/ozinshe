package com.example.ozinshe.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ozinshe.databinding.AdapterRcScreenItemBinding
import com.example.ozinshe.domain.model.ScreenshotXX
import com.example.ozinshe.presentation.home.interfaces.RcViewItemClickImageCallback

class ScreenItemAdapter(): RecyclerView.Adapter<ScreenItemAdapter.ScreenViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<ScreenshotXX>(){
        override fun areItemsTheSame(
            oldItem: ScreenshotXX,
            newItem: ScreenshotXX
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ScreenshotXX,
            newItem: ScreenshotXX
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    fun submitList(listScreenshots: List<ScreenshotXX>){
        differ.submitList(listScreenshots)
    }

    private var listenerClickAtItem: RcViewItemClickImageCallback? = null

    fun setOnImageClickListener(listener: RcViewItemClickImageCallback){
        this.listenerClickAtItem = listener
    }

    inner class ScreenViewHolder(private val binding: AdapterRcScreenItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindItem(image: ScreenshotXX) {
            Glide.with(itemView.context)
                .load(image.link)
                .into(binding.screenImg)

            itemView.setOnClickListener {
                listenerClickAtItem?.onClick(image.link)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenViewHolder {
        val binding = AdapterRcScreenItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScreenViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ScreenViewHolder, position: Int) {
        holder.bindItem(differ.currentList[position])
    }
}