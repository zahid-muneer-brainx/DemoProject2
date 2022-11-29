package com.example.demoproject2.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.demoproject2.R
import com.example.demoproject2.databinding.ChatItemViewBinding
import com.example.demoproject2.interfaces.onClickInterface

class ChatViewHolder (binding: ChatItemViewBinding,itemClickListener: onClickInterface) : RecyclerView.ViewHolder(binding.root) {
    // creating variables for our views.
    val contact_Name: TextView
    val last_Message: TextView
    val listener=itemView.setOnClickListener {
        itemClickListener.onClickItem(position)
    }


    init {
        // initializing our views with their ids.
        binding.apply {
            contact_Name=this.contactName
            last_Message=this.lastMessage
        }

    }

}