package com.tarms.bd.messagingapp

import android.app.Application
import android.util.Log
import com.google.firebase.database.FirebaseDatabase

class ApplicationPreferences : Application() {
    companion object {
        private lateinit var instance: ApplicationPreferences
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        Log.d("ApplicationPref", "<===============Application Started!=================>")
    }
}