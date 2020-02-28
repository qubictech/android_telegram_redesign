package com.tarms.bd.messagingapp

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tarms.bd.messagingapp.adapter.MainFragmentViewPagerAdapter
import com.tarms.bd.messagingapp.fragment.ChatListFragment
import com.tarms.bd.messagingapp.fragment.ContactListFragment
import com.tarms.bd.messagingapp.fragment.MoreFragment

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var pager: ViewPager
    private lateinit var mainFragmentViewPagerAdapter: MainFragmentViewPagerAdapter
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .add(R.id.container, ChatListFragment())
//                .commit()
//        }

        bottomNavigationView = findViewById(R.id.bottom_nav)
        bottomNavigationView.setOnNavigationItemSelectedListener(this)

        pager = findViewById(R.id.view_pager)

        val fragments = listOf(ChatListFragment(),ContactListFragment(), MoreFragment())

        mainFragmentViewPagerAdapter =
            MainFragmentViewPagerAdapter(supportFragmentManager,  fragments)

        pager.apply {
            adapter = mainFragmentViewPagerAdapter
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {

                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {

                }

                override fun onPageSelected(position: Int) {
                    when (position) {
                        0 -> bottomNavigationView.menu.getItem(0).isChecked = true
                        1 -> bottomNavigationView.menu.getItem(1).isChecked = true
                        2 -> bottomNavigationView.menu.getItem(2).isChecked = true
                        else -> bottomNavigationView.menu.getItem(position).isChecked = true
                    }
                }

            })
        }

    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.people -> pager.currentItem = 0

            R.id.message -> pager.currentItem = 1

            R.id.more -> pager.currentItem = 2
        }

        return true
    }
}
