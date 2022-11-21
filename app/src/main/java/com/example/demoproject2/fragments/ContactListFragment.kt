package com.example.demoproject2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.demoproject2.R
import android.widget.FrameLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoproject2.adapters.ChatMessageAdapter
import com.example.demoproject2.adapters.ChatPageAdapter
import com.example.demoproject2.adapters.TagRecyclerViewAdapter
import com.example.demoproject2.databinding.FragmentContactListBinding
import com.example.demoproject2.databinding.FragmentProfileBinding
import com.example.demoproject2.interfaces.ChatViewInterface
import com.example.demoproject2.models.ChatModel
import com.example.demoproject2.viewModels.ChatViewModel
import com.example.demoproject2.viewModels.LoginViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_contact_list.*
import kotlinx.android.synthetic.main.person_chat_fragment.*


class ContactListFragment : Fragment() {


     lateinit var binding:FragmentContactListBinding
    companion object {
        var shouldLoadOrganizationFragment = false
    }

    private val chatViewInterface = object : ChatViewInterface {
        override fun onSetOrganizationCount(count: Int) {

            organizationUnreadCount = count
            (sliding_tabs?.getTabAt(1)?.customView as ViewGroup?)?.findViewById<FrameLayout>(R.id.unread_count_layout)
                ?.visibility=if (count>0) View.VISIBLE else View.GONE

            (sliding_tabs?.getTabAt(1)?.customView as ViewGroup?)?.findViewById<TextView>(R.id.unread_count_tv)?.text =
                count.toString()
        }

        override fun onSetClientCount(count: Int) {

            clientUnreadCount = count
            (sliding_tabs?.getTabAt(0)?.customView as ViewGroup?)?.findViewById<FrameLayout>(R.id.unread_count_layout)
                ?.visibility=if (count>0) View.VISIBLE else View.GONE

            (sliding_tabs?.getTabAt(0)?.customView as ViewGroup?)?.findViewById<TextView>(R.id.unread_count_tv)?.text =
                count.toString()
    }

        override fun setScheduleMessageCount(count: Int) {
            TODO("Not yet implemented")
        }
    }

        private var chatPagerAdapter: ChatPageAdapter? = null
    private var fragmentList: ArrayList<Fragment> = ArrayList()
    private var fragmentsTitles: ArrayList<String> = ArrayList()
    private var organizationUnreadCount = 0
    private var clientUnreadCount = 0
    private var selectedFragmentIndex = 0
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentContactListBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabLayoutWithViewPagger()


    }



    override fun onResume() {
        super.onResume()
        if (shouldLoadOrganizationFragment) {
            shouldLoadOrganizationFragment = false
            view_pager.post {
                view_pager.setCurrentItem(1, true)
            }
        }
    }

    private fun setupTabLayoutWithViewPagger() {

        fragmentList.add(PersonChatFragment.newInstance(chatViewInterface))
        fragmentList.add(OrganizationChatFragment.newInstance(chatViewInterface))

        fragmentsTitles.add(getString(R.string.person))
        fragmentsTitles.add(getString(R.string.organization))

        view_pager.offscreenPageLimit = 2

        chatPagerAdapter = ChatPageAdapter(this@ContactListFragment)
        chatPagerAdapter?.apply {
            addFragments(fragmentList, fragmentsTitles)
            view_pager.adapter = this
        }
        TabLayoutMediator(sliding_tabs, view_pager) { tab, position ->
        }.attach()
        sliding_tabs?.addOnTabSelectedListener(onTabSelectedListener)

        setTabIcons()
        selectTab(0)
    }

    private fun setTabIcons() {
        (0 until sliding_tabs.tabCount).forEach { i ->
            sliding_tabs.getTabAt(i)?.apply {
                setCustomView(R.layout.note_tab_layout_item)

                customView?.findViewById<TextView>(R.id.title)?.text = fragmentsTitles[i]
            }
        }
    }

    fun selectTab(index: Int) {
        view_pager.currentItem = index
        changeTabColor(sliding_tabs?.getTabAt(index)?.customView as ViewGroup?, true)

        val shouldVisible = if (index == 0)
            clientUnreadCount != 0
        else
            organizationUnreadCount != 0

        (sliding_tabs?.getTabAt(index)?.customView as ViewGroup?)?.findViewById<FrameLayout>(R.id.unread_count_layout)
            ?.visibility=View.VISIBLE
    }


    private fun changeTabColor(customView: ViewGroup?, isSelected: Boolean) {
        (customView?.getChildAt(0) as TextView?)?.visibility=View.VISIBLE
        if (isSelected) {
            (customView?.getChildAt(0) as TextView?)?.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.primary_title
                )
            )
        } else {
            (customView?.getChildAt(0) as TextView?)
                ?.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_light_text))
        }
    }

    private var onTabSelectedListener: TabLayout.OnTabSelectedListener =
        object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                selectedFragmentIndex = tab.position
                changeTabColor(tab.customView as ViewGroup?, true)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                changeTabColor(tab.customView as ViewGroup?, false)
            }

            override fun onTabReselected(tab: TabLayout.Tab) = Unit
        }



    //endregion
}