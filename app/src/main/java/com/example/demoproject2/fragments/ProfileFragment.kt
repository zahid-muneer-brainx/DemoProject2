package com.example.demoproject2.fragments

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.example.demoproject2.R
import com.example.demoproject2.adapters.AlbumItemRecyclerViewAdapter
import com.example.demoproject2.adapters.MenuItemsRecyclerViewAdapter
import com.example.demoproject2.adapters.TagRecyclerViewAdapter
import com.example.demoproject2.databinding.FragmentProfileBinding
import com.example.demoproject2.databinding.RightMenuViewBinding
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.right_menu_view.*


// TODO: Rename parameter arguments, choose names that match

class ProfileFragment : Fragment() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var binding:FragmentProfileBinding

    var itemNames= arrayOf<String>("1. Panda Bao Bun","2. Fruit Loop Ice Cream","3. Panda Bao Bun","4. Fruit Loop Ice Cream")
    var albumNames= arrayOf<String>("Ice Cream","Waffles","Tapas")
    var topTagNames= arrayOf<String>("Timeline","Ratings","Recipes","Tags")
    lateinit var itemAdapter:MenuItemsRecyclerViewAdapter
    lateinit var albumAdapter:AlbumItemRecyclerViewAdapter
    lateinit var tagAdapter:TagRecyclerViewAdapter
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
    ): View? {
        binding=FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }


}