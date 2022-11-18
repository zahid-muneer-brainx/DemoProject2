package com.example.demoproject2.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.demoproject2.R
import com.example.demoproject2.fragments.OnBoardingFragment


class OnBoardingAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    var layouts: IntArray = intArrayOf(
        R.layout.onboarding_first,
        R.layout.onboarding_second,
        R.layout.onboarding_third
    )

    override fun getCount(): Int = layouts.size

    override fun getItem(i: Int): Fragment {
        val fragment = OnBoardingFragment()
        fragment.arguments = Bundle().apply {
            putInt("LAYOUT", layouts[i])
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return ""
    }
}