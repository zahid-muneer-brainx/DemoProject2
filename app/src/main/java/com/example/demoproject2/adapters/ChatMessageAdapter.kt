package com.example.demoproject2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoproject2.R
import com.example.demoproject2.models.ChatModel
import com.example.demoproject2.viewholders.ChatViewHolder
import com.example.demoproject2.viewholders.MenuItemViewHolder

class ChatMessageAdapter(
    private val chatDataList: ArrayList<ChatModel>
) : RecyclerView.Adapter<ChatViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatViewHolder {
        // Inflate Layout
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val myView = inflater.inflate(R.layout.chat_item_view, parent, false)
        return ChatViewHolder(myView)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        // Set the data to textview from our modal class.
        if (chatDataList[position].client != null) {
            holder.contactName.text = chatDataList[position].client?.name ?: ""
        } else {
            holder.contactName.text = chatDataList[position].id
        }
        holder.lastMessage.text = chatDataList[position].last_message.text
    }

    override fun getItemCount(): Int {
        // this method returns the size of recyclerview
        return chatDataList.size
    }

}