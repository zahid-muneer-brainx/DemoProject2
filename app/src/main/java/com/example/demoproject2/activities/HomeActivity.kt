package com.example.demoproject2.activities

import android.content.Context
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.demoproject2.R

import com.example.demoproject2.adapters.HomePageAdapter
import com.example.demoproject2.databinding.ActivityHomeBinding
import com.example.demoproject2.databinding.BottomNavLayoutBinding
import com.example.demoproject2.viewModels.HomeViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.bottom_nav_layout.view.*


class HomeActivity : AppCompatActivity() {
    private lateinit var mainPagerAdapter: HomePageAdapter
    lateinit var binding:ActivityHomeBinding
    lateinit var model:HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        model=ViewModelProvider(this).get(HomeViewModel::class.java)
        setViewPagerAdapter()
        setContentView(binding.root)
        supportActionBar?.hide()

    }
    private fun setViewPagerAdapter() {
        with(binding) {
            viewPager.offscreenPageLimit = 4
            mainPagerAdapter = HomePageAdapter(supportFragmentManager)
            mainPagerAdapter.apply {
                addFragments(model.fragmentList)
                viewPager.adapter = this
                bottomNavBar.setupWithViewPager(viewPager)
            }
            setTabs()
            selectTab(0)
            bottomNavBar.addOnTabSelectedListener(pagerTabListener)
        }

    }
    private fun setTabs() {
        binding.bottomNavBar.apply {
            repeat(tabCount){ tabIndex ->
                getTabAt(tabIndex)?.apply {
                    setCustomView(R.layout.bottom_nav_layout)
                    customView?.apply {
                        model.apply {
                            item_icon.setImageResource(tabIcons[tabIndex])

                        }
                    }
                }
            }

        }

    }
    private fun selectTab(index: Int) {
        binding.apply {
            viewPager.currentItem = index
            changeTabIconAndTextColor(bottomNavBar.getTabAt(index)?.customView, true,index)
        }

    }
    private fun changeTabIconAndTextColor(customView: View?, isSelected: Boolean, position: Int) {

//        val iconColor = if (isSelected) R.color.white_transparent else R.color.transparent_black
//        customView?.apply {
//
//            item_icon.colorFilter =
//                getFilterColor(
//                    this.context,
//                    iconColor
//                )
//
//        }

    }
    private val pagerTabListener = object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            tab.apply {
                changeTabIconAndTextColor(customView, true,position)
            }

        }

        override fun onTabUnselected(tab: TabLayout.Tab) {
            tab.apply {
                changeTabIconAndTextColor(customView, false, position)
            }
        }

        override fun onTabReselected(tab: TabLayout.Tab) = Unit
    }
    fun getFilterColor(context: Context, colorId: Int): ColorFilter {
        return PorterDuffColorFilter(
            ContextCompat.getColor(
                context,
                colorId
            ), PorterDuff.Mode.SRC_ATOP
        )
    }
}