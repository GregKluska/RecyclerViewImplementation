package com.gregkluska.recyclerviewimplementation.ui.main

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.gregkluska.recyclerviewimplementation.R
import com.gregkluska.recyclerviewimplementation.models.Photo
import kotlinx.android.synthetic.main.layout_photo_item.view.*
import javax.inject.Inject

class MainRecyclerAdapter(
    private val requestManager: RequestManager,
    private val interaction: Interaction? = null
    ) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Photo>() {

        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return PhotoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_photo_item,
                parent,
                false
            ),
            requestManager,
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PhotoViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Photo>) {
        differ.submitList(list)
    }

    class PhotoViewHolder
    constructor(
        itemView: View,
        val requestManager: RequestManager,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Photo) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            requestManager
                .load(item.url)
                .into(itemView.photo_image)

            itemView.photo_title.text = item.title

        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Photo)
    }
}