package com.tarms.bd.messagingapp.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tarms.bd.messagingapp.R
import com.tarms.bd.messagingapp.fragment.sign.SignInFragment

class UserSignActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_sign)

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, SignInFragment()).commit()
    }
}
