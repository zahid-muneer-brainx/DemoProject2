package com.example.demoproject2.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoproject2.R

class TagItemViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    // creating variables for our views.
    val itemName: TextView


    init {
        // initializing our views with their ids.
        itemName = itemView.findViewById(R.id.tag_name)

    }


}