package com.example.demoproject2.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ChatPageAdapter: FragmentStateAdapter {

    constructor(fragmentActivity: FragmentActivity) : super(fragmentActivity)
    constructor(fragment: Fragment) : super(fragment)

    private lateinit var fragmentList: List<Fragment>
    private lateinit var titleList: List<String>
    fun addFragments(fragments: List<Fragment>, tiles: List<String>) {
        fragmentList = fragments
        titleList = tiles
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }


    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}