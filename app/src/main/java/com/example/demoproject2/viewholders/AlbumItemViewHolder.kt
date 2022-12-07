package com.example.demoproject2.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoproject2.R

class AlbumItemViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    // creating variables for our views.
    val itemImage: ImageView
    val itemName: TextView


    init {
        // initializing our views with their ids.
        itemImage = itemView.findViewById(R.id.album_image)
        itemName = itemView.findViewById(R.id.album_name)

    }

}