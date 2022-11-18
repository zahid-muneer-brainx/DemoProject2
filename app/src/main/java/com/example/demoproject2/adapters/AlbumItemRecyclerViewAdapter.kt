package com.example.demoproject2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoproject2.R
import com.example.demoproject2.databinding.AlbumItemViewProfileBinding
import com.example.demoproject2.databinding.ItemViewProfileBinding
import com.example.demoproject2.viewholders.AlbumItemViewHolder
import com.example.demoproject2.viewholders.MenuItemViewHolder

class AlbumItemRecyclerViewAdapter (
    private val contactDataArrayList: Array<String>

) : RecyclerView.Adapter<AlbumItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumItemViewHolder {
        // Inflate Layout
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val myView = inflater.inflate(R.layout.album_item_view_profile, parent, false)
        return AlbumItemViewHolder(myView)
    }

    override fun onBindViewHolder(holder: AlbumItemViewHolder, position: Int) {
        // Set the data to textview from our modal class.
        holder.itemImage.setImageResource(R.drawable.image_165)
        holder.itemName.text = contactDataArrayList[position]

    }

    override fun getItemCount(): Int {
        // this method returns the size of recyclerview
        return contactDataArrayList.size
    }

}