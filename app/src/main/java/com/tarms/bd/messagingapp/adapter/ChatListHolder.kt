package com.tarms.bd.messagingapp.adapter

import androidx.recyclerview.widget.RecyclerView
import com.tarms.bd.messagingapp.data.Chat
import com.tarms.bd.messagingapp.databinding.ChatListItemBinding

class ChatListHolder(private val binding: ChatListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun chats(chat: Chat) {
        binding.chat = chat
    }
}