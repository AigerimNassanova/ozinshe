package com.example.ozinshe.domain.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ozinshe.databinding.ItemWelcomepageSliderBinding
import com.example.ozinshe.domain.model.WelcomePageInfoModel

class WelcomePageAdapter(private val welcomePageInfoList: List<WelcomePageInfoModel>) :
    RecyclerView.Adapter<WelcomePageAdapter.WelcomePageViewHolder>() {

    inner class WelcomePageViewHolder(private val binding: ItemWelcomepageSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(info: WelcomePageInfoModel) {
            binding.textTvTitleWelcomePageSlider.text = info.title
            binding.textTvDescriptionWelcomePageSlider.text = info.description
            binding.imgTvWelcomeBannerOpacityBack.setBackgroundResource(info.img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WelcomePageViewHolder {
        return WelcomePageViewHolder(
            ItemWelcomepageSliderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return welcomePageInfoList.size
    }

    override fun onBindViewHolder(holder: WelcomePageViewHolder, position: Int) {
        holder.bindItem(welcomePageInfoList[position])
    }
}