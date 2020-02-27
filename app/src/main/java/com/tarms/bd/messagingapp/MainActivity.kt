package com.tarms.bd.messagingapp

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tarms.bd.messagingapp.fragment.ChatListFragment
import com.tarms.bd.messagingapp.fragment.ContactListFragment

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, ChatListFragment())
                .commit()
        }

        findViewById<BottomNavigationView>(R.id.bottom_nav).setOnNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.people -> supportFragmentManager.beginTransaction()
                .replace(R.id.container, ContactListFragment())
                .commit()

            R.id.message -> supportFragmentManager.beginTransaction()
                .replace(R.id.container, ChatListFragment())
                .commit()
        }

        return true
    }
}
