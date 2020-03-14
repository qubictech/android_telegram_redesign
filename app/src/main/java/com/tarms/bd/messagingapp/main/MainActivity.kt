package com.tarms.bd.messagingapp.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tarms.bd.messagingapp.R
import com.tarms.bd.messagingapp.adapter.MainFragmentViewPagerAdapter
import com.tarms.bd.messagingapp.fragment.main.ChatListFragment
import com.tarms.bd.messagingapp.fragment.main.ContactListFragment
import com.tarms.bd.messagingapp.fragment.main.MoreFragment
import com.tarms.bd.messagingapp.fragment.sign.AuthenticationFragment
import com.tarms.bd.messagingapp.utils.FirebaseUtil
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    private lateinit var pager: ViewPager
    private lateinit var mainFragmentViewPagerAdapter: MainFragmentViewPagerAdapter
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_nav)
        bottomNavigationView.setOnNavigationItemSelectedListener { p0 ->
            when (p0.itemId) {
                R.id.message -> pager.setCurrentItem(0, true)
                R.id.people -> pager.setCurrentItem(1, true)
                R.id.more -> pager.setCurrentItem(2, true)
            }

            true
        }

        pager = findViewById(R.id.view_pager)

        val fragments = listOf(ChatListFragment(),
            ContactListFragment(),
            MoreFragment()
        )

        mainFragmentViewPagerAdapter =
            MainFragmentViewPagerAdapter(supportFragmentManager, fragments)

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

        val badge = bottomNavigationView.getOrCreateBadge(R.id.more)
        badge.isVisible = true
        badge.backgroundColor = resources.getColor(R.color.colorPrimary)

        val message = bottomNavigationView.getOrCreateBadge(R.id.message)
        message.isVisible = true
        message.number = 10
    }
}
