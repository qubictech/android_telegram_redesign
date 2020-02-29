package com.tarms.bd.messagingapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tarms.bd.messagingapp.data.Chat
import java.util.*

object MyRepository {
    private val allChats = mutableListOf(
        Chat(
            "sabbir",
            "Ahsan", "We'll meet tomorrow!",
            Date().time,
            1
        ),
        Chat(
            "sabbir",
            "Sabbir", "Hi! How was your day?",
            Date().time,
            0
        ),
        Chat(
            "sabbir",
            "Saharia", "Have you taken your dinner?",
            Date().time,
            2
        ),
        Chat(
            "sabbir",
            "Aysha", "What about your study?",
            Date().time,
            0
        ),
        Chat(
            "sabbir",
            "Rabin", "Okay! Bye",
            Date().time,
            0
        ),
        Chat(
            "sabbir",
            "Milon", "Okay! See you soon.",
            Date().time,
            1
        )
    )

    private val friendChat = mutableListOf(
        Chat(
            "sabbir",
            "Rabin", "Okay! Bye",
            Date().time,
            0
        ),
        Chat(
            "sabbir",
            "Milon", "Okay! See you soon.",
            Date().time,
            1
        )
    )

    private val workChat = mutableListOf(
        Chat(
            "sabbir",
            "Ahsan", "We'll meet tomorrow!",
            Date().time,
            1
        ),
        Chat(
            "sabbir",
            "Habib", "Hei bro! How are you?",
            Date().time,
            0
        )
    )

    private val list = HashMap<String, MutableLiveData<List<Chat>>>()

    private val groupList = MutableLiveData<List<String>>()

    init {
        setChatInGroup("All", allChats)
        setChatInGroup("Friends", friendChat)
        setChatInGroup("Work", workChat)

        setGroup(
            listOf(
                "All",
                "Friends",
                "Work"
            )
        )
    }

    fun setChatInGroup(group: String, chats: List<Chat>) {
        chats.let {
            val chat = MutableLiveData<List<Chat>>()

            chat.value = it
            list[group] = chat
        }
    }

    fun getChatListByGroup(group: String): LiveData<List<Chat>> {
        return list[group]
            ?: throw NullPointerException("Expression 'list[group]' must not be null")
    }

    fun setGroup(group: List<String>) {
        groupList.value = group
    }

    fun getGroup(): LiveData<List<String>> {
        return groupList
    }
}