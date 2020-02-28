package com.tarms.bd.messagingapp.utils

import android.view.View
import com.tarms.bd.messagingapp.data.Chat

interface ChatItemClickEventListener {
    fun onItemClick(view:View,chat: Chat)
}