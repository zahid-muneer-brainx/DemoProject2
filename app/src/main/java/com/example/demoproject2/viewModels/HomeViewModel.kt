package com.example.demoproject2.viewModels

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.demoproject2.R
import com.example.demoproject2.fragments.ContactListFragment
import com.example.demoproject2.fragments.LogoutFragment
import com.example.demoproject2.fragments.PhoneNumberFragment
import com.example.demoproject2.fragments.ProfileFragment

class HomeViewModel:ViewModel() {

    var fragmentList: MutableList<Fragment> = mutableListOf()
    val tabIcons: MutableList<Int> = mutableListOf()
    init {
        fragmentList.apply {
            add(PhoneNumberFragment())
            add(ProfileFragment())
            add(ContactListFragment())
            add(LogoutFragment())
        }
        tabIcons.apply {
            add(R.drawable.image_175)
            add(R.drawable.ic_rectangle_158)
            add(R.drawable.ic_like)
            add(R.drawable.cravve_icon_1)
        }


    }

}