package com.example.demoproject2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoproject2.R
import com.example.demoproject2.viewholders.MenuItemViewHolder

class MenuItemsRecyclerViewAdapter (
    private val contactDataArrayList: Array<String>

) :

    RecyclerView.Adapter<MenuItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuItemViewHolder {
        // Inflate Layout
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val myView = inflater.inflate(R.layout.item_view_profile, parent, false)
        return MenuItemViewHolder(myView)
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        // Set the data to textview from our modal class.
        holder.itemImage.setImageResource(R.drawable.image_199)
        holder.itemName.text = contactDataArrayList[position]

    }

    override fun getItemCount(): Int {
        // this method returns the size of recyclerview
        return contactDataArrayList.size
    }

}