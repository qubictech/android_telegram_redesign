package com.tarms.bd.messagingapp.repository

import androidx.lifecycle.ViewModel
import com.tarms.bd.messagingapp.data.Chat

class MyViewModel : ViewModel() {

    fun setChatList(group: String, values: List<Chat>) {
        MyRepository.setChatInGroup(group, values)
    }

    fun getChatList(type: String) = MyRepository.getChatListByGroup(type)
}