package com.example.picture_switcher.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.picture_switcher.R
import com.example.picture_switcher.data.UnsplashPhoto
import com.squareup.picasso.Picasso

class PictureAdapter(
    private val isRandomPhoto: Boolean,
    private val pictureList: MutableList<UnsplashPhoto>,
    private val needNewPage: NeedNewPage,
    private val onImageClickCallback: OnImageClickCallback
) :
    RecyclerView.Adapter<PictureAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = pictureList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = pictureList[position]

        Picasso.get().load(listItem.urls.regular).into(holder.photoImageView)

        holder.photoImageView.setOnClickListener {
            onImageClickCallback.onImageClick(listItem)
        }

        if (position == pictureList.size - 1) {
            if (isRandomPhoto) {
                needNewPage.nextPageRandom()
            } else {
                needNewPage.nextPageSearch()
            }

        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val photoImageView: ImageView = view.findViewById(R.id.picture_image_view)
    }

    interface NeedNewPage {
        fun nextPageRandom()
        fun nextPageSearch()
    }

    interface OnImageClickCallback {
        fun onImageClick(unsplashPhoto: UnsplashPhoto)
    }
}
