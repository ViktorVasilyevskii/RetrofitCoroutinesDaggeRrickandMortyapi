package com.vasilyevskii.retrofitcoroutinesdagger.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.vasilyevskii.retrofitcoroutinesdagger.databinding.ActivityRickMortyBinding
import com.vasilyevskii.retrofitcoroutinesdagger.models.RickMorty
import com.vasilyevskii.retrofitcoroutinesdagger.adapter.RickMortyAdapter.ViewHolder

class RickMortyAdapter: PagingDataAdapter<RickMorty, ViewHolder>(diffCallback) {

    inner class ViewHolder(val binding: ActivityRickMortyBinding):
    RecyclerView.ViewHolder(binding.root)

    companion object {
        val diffCallback = object: DiffUtil.ItemCallback<RickMorty>(){
            override fun areItemsTheSame(oldItem: RickMorty, newItem: RickMorty): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RickMorty, newItem: RickMorty): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)

        holder.binding.apply {

            textViewName.text = "${currentItem?.name}"
            val imageLink = currentItem?.image

            imageViewAvatar.load(imageLink){
                crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ActivityRickMortyBinding.inflate(LayoutInflater.from(parent.context)))
    }
}