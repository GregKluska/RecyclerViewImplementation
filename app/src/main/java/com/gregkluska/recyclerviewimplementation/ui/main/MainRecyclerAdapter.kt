package com.gregkluska.recyclerviewimplementation.ui.main

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.gregkluska.recyclerviewimplementation.R
import com.gregkluska.recyclerviewimplementation.models.Album
import com.gregkluska.recyclerviewimplementation.models.Photo
import com.gregkluska.recyclerviewimplementation.models.User
import com.gregkluska.recyclerviewimplementation.util.Constants.Companion.USER_AGENT
import kotlinx.android.synthetic.main.layout_album_item.view.*
import kotlinx.android.synthetic.main.layout_photo_item.view.*
import kotlinx.android.synthetic.main.layout_user_item.view.*
import javax.inject.Inject

class MainRecyclerAdapter(
    private val requestManager: RequestManager,
    private val interaction: Interaction? = null
    ) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val USER_TYPE = 1
        const val ALBUM_TYPE = 2
        const val PHOTO_TYPE = 3
    }

    val USER_DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    val ALBUM_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem == newItem
        }
    }

    val PHOTO_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }

    private val userDiffer = AsyncListDiffer(this, USER_DIFF_CALLBACK)
    private val albumDiffer = AsyncListDiffer(this, ALBUM_DIFF_CALLBACK)
    private val photoDiffer = AsyncListDiffer(this, PHOTO_DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view : View

        when (viewType) {
            USER_TYPE -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.layout_user_item,
                    parent,
                    false
                )
                return UserViewHolder(view, interaction)
            }
            ALBUM_TYPE -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.layout_album_item,
                    parent,
                    false
                )
                return AlbumViewHolder(view, interaction)
            }

            PHOTO_TYPE -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.layout_photo_item,
                    parent,
                    false
                )
                return PhotoViewHolder(view, requestManager)
            }

            else -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.layout_user_item,
                    parent,
                    false
                )
                return UserViewHolder(view, interaction)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserViewHolder -> {
                holder.bind(userDiffer.currentList[position])
            }

            is AlbumViewHolder -> {
                holder.bind(albumDiffer.currentList[position])
            }

            is PhotoViewHolder -> {
                holder.bind(photoDiffer.currentList[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(photoDiffer.currentList.size > 0) {
            return PHOTO_TYPE
        } else if(albumDiffer.currentList.size > 0) {
            return ALBUM_TYPE
        }
        return USER_TYPE
    }

    override fun getItemCount(): Int {
        return userDiffer.currentList.size + albumDiffer.currentList.size + photoDiffer.currentList.size
    }

    fun submitUserList(list: List<User>) {
        albumDiffer.submitList(null)
        photoDiffer.submitList(null)
        userDiffer.submitList(list)
    }

    fun submitAlbumList(list: List<Album>) {
        userDiffer.submitList(null)
        photoDiffer.submitList(null)
        albumDiffer.submitList(list)
    }

    fun submitPhotoList(list: List<Photo>) {
        userDiffer.submitList(null)
        albumDiffer.submitList(null)
        photoDiffer.submitList(list)
    }

    class UserViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: User) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onUserSelected(adapterPosition, item)
            }

            itemView.username.text = item.username
            itemView.name.text = item.name
        }

    }

    class AlbumViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Album) = with(itemView) {
            itemView.setOnClickListener{
                interaction?.onAlbumSelected(adapterPosition, item)
            }

            itemView.album_title.text = item.title
        }

    }


    class PhotoViewHolder
    constructor(
        itemView: View,
        private val requestManager: RequestManager
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Photo) = with(itemView) {

             val glideUrl: GlideUrl = GlideUrl(item.url, LazyHeaders.Builder()
                .addHeader("User-Agent", USER_AGENT)
                .build()
            )

            requestManager
                .load(glideUrl)
                .into(itemView.photo_image)

            itemView.photo_title.text = item.title

        }
    }

    interface Interaction {
        fun onAlbumSelected(position: Int, album: Album)
        fun onUserSelected(position: Int, user: User)
    }
}