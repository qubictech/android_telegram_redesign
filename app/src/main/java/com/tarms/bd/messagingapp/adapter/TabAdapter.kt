package com.tarms.bd.messagingapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.tarms.bd.messagingapp.fragment.tabs.ChatTabFragment

class TabAdapter(fm: FragmentManager, private var tabCount: Int) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ChatTabFragment()
            1 -> ChatTabFragment()
            2 -> ChatTabFragment()
            3 -> ChatTabFragment()
            else -> ChatTabFragment()
        }
    }

    override fun getCount() = tabCount
}