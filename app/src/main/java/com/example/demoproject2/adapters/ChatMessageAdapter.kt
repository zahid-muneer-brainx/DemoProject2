package com.example.demoproject2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoproject2.R
import com.example.demoproject2.databinding.ChatItemViewBinding
import com.example.demoproject2.interfaces.onClickInterface
import com.example.demoproject2.models.ChatModel
import com.example.demoproject2.viewholders.ChatViewHolder
import com.example.demoproject2.viewholders.MenuItemViewHolder

class ChatMessageAdapter(
    private val itemClickListener: onClickInterface,
    private val chatDataList: ArrayList<ChatModel>
) : RecyclerView.Adapter<ChatViewHolder>() {
lateinit var chatItemViewBinding: ChatItemViewBinding
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatViewHolder {
        // Inflate Layout
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        chatItemViewBinding=ChatItemViewBinding.inflate(inflater)
        return ChatViewHolder(chatItemViewBinding,itemClickListener)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        // Set the data to textview from our modal class.
        if (chatDataList[position].client != null) {
            holder.contact_Name.text = chatDataList[position].client?.name ?: ""
        } else {
            holder.contact_Name.text = chatDataList[position].id
        }
        var msgToDisplay="..."
        if(chatDataList[position].last_message.text.length>27)
        {
            msgToDisplay=chatDataList[position].last_message.text.substring(0,26)+msgToDisplay
            holder.last_Message.text = msgToDisplay

        }
        else
            holder.last_Message.text = chatDataList[position].last_message.text

    }


    override fun getItemCount(): Int {
        // this method returns the size of recyclerview
        return chatDataList.size
    }

}