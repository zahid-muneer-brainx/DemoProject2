package com.example.demoproject2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoproject2.R
import com.example.demoproject2.viewholders.TagItemViewHolder

class TagRecyclerViewAdapter(
    private val contactDataArrayList: Array<String>

) : RecyclerView.Adapter<TagItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TagItemViewHolder {
        // Inflate Layout
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val myView = inflater.inflate(R.layout.top_tags_item_view, parent, false)
        return TagItemViewHolder(myView)
    }

    override fun onBindViewHolder(holder: TagItemViewHolder, position: Int) {
        // Set the data to textview from our modal class.

        holder.itemName.text = contactDataArrayList[position]
        holder.itemName.setOnClickListener {
            holder.itemName.setBackgroundResource(R.drawable.ic_rectangle_195)
        }
        holder.itemName.setBackgroundResource(R.drawable.ic_rectangle_196)

    }

    override fun getItemCount(): Int {
        // this method returns the size of recyclerview
        return contactDataArrayList.size
    }

}