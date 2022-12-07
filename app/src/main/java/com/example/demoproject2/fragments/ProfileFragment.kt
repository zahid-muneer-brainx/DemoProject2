package com.example.demoproject2.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.example.demoproject2.R
import com.example.demoproject2.adapters.AlbumItemRecyclerViewAdapter
import com.example.demoproject2.adapters.MenuItemsRecyclerViewAdapter
import com.example.demoproject2.adapters.TagRecyclerViewAdapter
import com.example.demoproject2.databinding.FragmentProfileBinding



// TODO: Rename parameter arguments, choose names that match

class ProfileFragment : Fragment() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var binding:FragmentProfileBinding

    private var itemNames= arrayOf<String>("1. Panda Bao Bun","2. Fruit Loop Ice Cream","3. Panda Bao Bun","4. Fruit Loop Ice Cream")
    private var albumNames= arrayOf<String>("Ice Cream","Waffles","Tapas")
    private var topTagNames= arrayOf<String>("Timeline","Ratings","Recipes","Tags")
    private lateinit var itemAdapter:MenuItemsRecyclerViewAdapter
    private lateinit var albumAdapter:AlbumItemRecyclerViewAdapter
    private lateinit var tagAdapter:TagRecyclerViewAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        drawerLayout = binding.drawable
        actionBarDrawerToggle = ActionBarDrawerToggle(requireActivity(), drawerLayout, R.string.nav_open, R.string.nav_close)

        binding.menuSlider.setOnClickListener {
            drawerLayout.openDrawer(Gravity.RIGHT)
        }

        // to make the Navigation drawer icon always appear on the action bar
        displayItems()
        displayAlbums()
        displayTags()
    }

    private fun displayTags() {
        tagAdapter = TagRecyclerViewAdapter(topTagNames)
        val manager = LinearLayoutManager(requireContext(), HORIZONTAL, false)
        binding.RTTopTags.layoutManager = manager
        binding.RTTopTags.adapter = tagAdapter
    }

    private fun displayAlbums() {
        albumAdapter = AlbumItemRecyclerViewAdapter(albumNames)
        val manager = LinearLayoutManager(requireContext(), HORIZONTAL, false)
        binding.RTAlbumView.layoutManager = manager
        binding.RTAlbumView.adapter = albumAdapter
    }

    private fun displayItems() {

            itemAdapter = MenuItemsRecyclerViewAdapter(itemNames)
            val manager = LinearLayoutManager(requireContext(), HORIZONTAL, false)
            binding.RTView.layoutManager = manager
            binding.RTView.adapter = itemAdapter

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }


}