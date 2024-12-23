package com.example.ozinshe.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ozinshe.databinding.AdapterRcSeasonNumberItemBinding

class SeasonNumberItemAdapter(): RecyclerView.Adapter<SeasonNumberItemAdapter.SeasonNumberViewHolder>() {

    inner class SeasonNumberViewHolder(private val binding: AdapterRcSeasonNumberItemBinding): RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonNumberViewHolder {
        val binding = AdapterRcSeasonNumberItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeasonNumberViewHolder(binding)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: SeasonNumberViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}