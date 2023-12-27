package com.example.pixabay_hw_3_5

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pixabay_hw_3_5.databinding.ItemImageBinding

class ImageAdapter(private val list: MutableList<ImageModel>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addNewImages(newList: List<ImageModel>) {
        list.addAll(newList)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun listClear() {
        list.clear()
        notifyDataSetChanged()
    }

    inner class ImageViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(image: ImageModel) {
            binding.ivPix.load(image.largeImageURL)
        }
    }
}