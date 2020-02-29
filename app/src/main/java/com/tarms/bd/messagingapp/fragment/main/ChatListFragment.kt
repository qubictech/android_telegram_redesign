package com.tarms.bd.messagingapp.fragment.main


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.tarms.bd.messagingapp.R
import com.tarms.bd.messagingapp.adapter.ChatTabAdapter
import com.tarms.bd.messagingapp.fragment.tabs.ChatTabFragment
import com.tarms.bd.messagingapp.repository.MyViewModel


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
        setHasOptionsMenu(true)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)

        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)

        val viewPager = view.findViewById<ViewPager>(R.id.view_pager)
        val tabs = view.findViewById<TabLayout>(R.id.tabs)

        val chatGroups = mutableListOf<String>()

        val viewModelProvider = ViewModelProvider(this).get(MyViewModel::class.java)

        viewModelProvider.getGroups().observe(viewLifecycleOwner, Observer { groups ->
            val fragments = mutableListOf<Fragment>()
            tabs.removeAllTabs()

            for (group in groups) {
                chatGroups.add(group)

                tabs.addTab(tabs.newTab())
                tabs.getTabAt(tabs.tabCount - 1)?.text = group

                fragments.add(ChatTabFragment.newInstance(group))
            }

            val tabAdapter = ChatTabAdapter(childFragmentManager, fragments)

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
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_btn, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.add_new_group)
            Toast.makeText(context, "Add New Chat Group", Toast.LENGTH_SHORT).show()

        if (item.itemId == android.R.id.home)
            Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show()

        return super.onOptionsItemSelected(item)
    }

}
