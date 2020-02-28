package com.tarms.bd.messagingapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.tarms.bd.messagingapp.data.Chat
import com.tarms.bd.messagingapp.databinding.ChatListItemBinding
import com.tarms.bd.messagingapp.main.ChatActivity
import com.tarms.bd.messagingapp.utils.ChatItemClickEventListener
import java.util.logging.Logger

class ChatListAdapter(private val context: Context, private val chatlist: List<Chat>) :
    RecyclerView.Adapter<ChatListAdapter.ChatListHolder>(), ChatItemClickEventListener {

    inner class ChatListHolder(private val binding: ChatListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun chats(chat: Chat) {
            binding.chat = chat
        }
    }

    override fun onItemClick(view: View, chat: Chat) {
        Logger.getLogger("onItemClick").warning("------------------> Clicked! ${chat.unreadMsgCount}")
        startActivity(context, Intent(context, ChatActivity::class.java), null)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListHolder {

        val inflater = LayoutInflater.from(parent.context)
        val chatListItemBinding = ChatListItemBinding.inflate(inflater, parent, false)

        chatListItemBinding.apply {
            this.clickEvent = this@ChatListAdapter
        }
        return ChatListHolder(chatListItemBinding)
    }

    override fun getItemCount() = chatlist.size

    override fun onBindViewHolder(holder: ChatListHolder, position: Int) {
        holder.chats(chatlist[position])
    }
}