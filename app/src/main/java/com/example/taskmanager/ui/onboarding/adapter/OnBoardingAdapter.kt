package com.example.taskmanager.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskmanager.databinding.ItemOnboardingBinding
import com.example.taskmanager.model.OnBoarding
import com.example.taskmanager.utils.loadImage

class OnBoardingAdapter(private val onClick: () ->Unit): Adapter<OnBoardingAdapter.OnBoardingViewHolder>(){

    private val list = arrayListOf(
        OnBoarding("https://upload.wikimedia.org/wikipedia/en/0/08/21_Savage_and_Metro_Boomin_-_Savage_Mode_II.png","Title 1","Desc 1"),
        OnBoarding("https://upload.wikimedia.org/wikipedia/ru/1/10/Testing_%28%D0%B0%D0%BB%D1%8C%D0%B1%D0%BE%D0%BC%29.jpg","Title 2","Desc 2"),
        OnBoarding("https://i1.sndcdn.com/artworks-rcsPtZDYqG1n-0-t500x500.jpg","Title3","Desc 3")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
 return OnBoardingViewHolder(ItemOnboardingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
return list.size
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(list.get(position))
    }
    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding):
        ViewHolder(binding.root) {
            fun bind(onBoarding: OnBoarding) = with(binding){
           tvTitle.text = onBoarding.title
                tvDesc.text = onBoarding.desc
ivBoard.loadImage(onBoarding.image)
               btnStart.setOnClickListener{ onClick() }
                skip.setOnClickListener{ onClick() }
                  skip.isInvisible=adapterPosition==list.lastIndex
           btnStart.isVisible=adapterPosition==list.lastIndex
            }
    }
    }
