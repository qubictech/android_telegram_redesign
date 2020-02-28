package com.tarms.bd.messagingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tarms.bd.messagingapp.data.Chat
import com.tarms.bd.messagingapp.databinding.ChatListItemBinding

class ChatListAdapter(private val chatlist: List<Chat>) : RecyclerView.Adapter<ChatListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListHolder {

        val inflater = LayoutInflater.from(parent.context)
        val chatListItemBinding = ChatListItemBinding.inflate(inflater, parent, false)

        return ChatListHolder(chatListItemBinding)
    }

    override fun getItemCount() = chatlist.size

    override fun onBindViewHolder(holder: ChatListHolder, position: Int) {
        holder.chats(chatlist[position])
    }
}