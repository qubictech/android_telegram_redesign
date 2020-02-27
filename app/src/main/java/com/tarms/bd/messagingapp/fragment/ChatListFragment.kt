package com.tarms.bd.messagingapp.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.tarms.bd.messagingapp.R
import com.tarms.bd.messagingapp.adapter.TabAdapter

/**
 * A simple [Fragment] subclass.
 */
class ChatListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = view.findViewById<ViewPager>(R.id.view_pager)
        val tabs = view.findViewById<TabLayout>(R.id.tabs)

        val tabAdapter = TabAdapter(childFragmentManager, tabs.tabCount)

        val tabLayout = object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

        }

        tabs.apply {
            addOnTabSelectedListener(tabLayout)
        }

        viewPager.apply {
            adapter = tabAdapter
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        }
    }

}
