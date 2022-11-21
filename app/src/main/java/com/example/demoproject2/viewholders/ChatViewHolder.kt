package com.example.demoproject2.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoproject2.R

class ChatViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    // creating variables for our views.
    val contactName: TextView
    val lastMessage: TextView


    init {
        // initializing our views with their ids.
        contactName = itemView.findViewById(R.id.contact_name)
        lastMessage = itemView.findViewById(R.id.last_message)

    }

}