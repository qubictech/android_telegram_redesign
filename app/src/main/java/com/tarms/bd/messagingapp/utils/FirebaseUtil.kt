package com.tarms.bd.messagingapp.utils

import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.tarms.bd.messagingapp.data.User
import com.tarms.bd.messagingapp.main.MainActivity
import java.lang.NullPointerException

object FirebaseUtil {
    private val user = FirebaseAuth.getInstance().currentUser
    private val reference: DatabaseReference
        get() = FirebaseDatabase.getInstance()
            .reference.child("_users/${user?.uid ?: throw NullPointerException("UID is null")}/")

    fun isExistedUser(onDataChange: (Boolean) -> Unit) {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                error.toException().printStackTrace()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var available = false

                snapshot.children.forEach {
                    it.getValue(String::class.java)?.let {
                        available = true
                    }
                }

                onDataChange(available)
            }
        })
    }

    fun createNewUser(user: User) {
        reference.setValue(user)
    }
}