package com.tarms.bd.messagingapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tarms.bd.messagingapp.data.Chat
import java.util.*

object MyRepository {
    private val list = HashMap<String, MutableLiveData<List<Chat>>>()

    private val groupList = MutableLiveData<List<String>>()

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