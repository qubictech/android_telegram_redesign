package com.tarms.bd.messagingapp.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.tarms.bd.messagingapp.data.User
import java.lang.NullPointerException

object FirebaseUtil {
    private val firestoreInstance: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }
    private val user = FirebaseAuth.getInstance().currentUser
    private val reference: DatabaseReference
        get() = FirebaseDatabase.getInstance()
            .reference.child("_users/${user?.uid ?: throw NullPointerException("UID is null")}/")

    private val currentUserDocRef: DocumentReference
        get() = firestoreInstance.document(
            "users/${FirebaseAuth.getInstance().currentUser?.uid
                ?: throw NullPointerException("UID is null")}"
        )

    private val chatChannelsCollectionRef = firestoreInstance.collection("chatChannels")

    fun getOrCreateUser(onComplete: (Boolean) -> Unit) {
        currentUserDocRef.get().addOnSuccessListener { documentSnapshot ->
            if (!documentSnapshot.exists()){
                val newUser = User(FirebaseAuth.getInstance().currentUser?.displayName?:"","")
                currentUserDocRef.set(newUser).addOnSuccessListener {
                    onComplete(false)
                }
            }else onComplete(true)
        }
    }

    fun createNewUser(user: User) {
        reference.setValue(user)
    }

    fun createNewChatChannel() {

    }

}