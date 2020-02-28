package com.tarms.bd.messagingapp.fragment.tabs


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tarms.bd.messagingapp.R
import com.tarms.bd.messagingapp.adapter.ChatListAdapter
import com.tarms.bd.messagingapp.data.Chat
import java.util.*

class ChatTabFragment : Fragment() {

    companion object {
        const val ARGS = "f_name"

        fun newInstance(fragmentName: String) = ChatTabFragment().apply {

            arguments = bundleOf(
                ARGS to fragmentName
            )
        }
    }

    private lateinit var fragmentName: String
    private lateinit var recyclerView: RecyclerView
    private lateinit var chatListAdapter: ChatListAdapter
    private val chatList = mutableListOf<Chat>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            fragmentName = it.getString(ARGS).toString()
        }
        recyclerView = view.findViewById(R.id.recycler_view)

        chatList(fragmentName)
    }

    private fun chatList(type: String) {
        chatList.clear()
        chatListAdapter = context?.let { ChatListAdapter(it, chatList) }!!

        recyclerView.apply {
            adapter = chatListAdapter
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
        }

        when (type) {
            "All" -> {
                chatList.add(
                    Chat(
                        "sabbir",
                        "Sabbir", "Hi! How was your day?",
                        Date().time,
                        0
                        )
                )

                chatList.add(
                    Chat(
                        "sabbir",
                        "Saharia", "Have you taken your dinner?",
                        Date().time,
                        2
                    )
                )

                chatList.add(
                    Chat(
                        "sabbir",
                        "Aysha", "What about your study?",
                        Date().time,
                        0
                    )
                )

                chatList.add(
                    Chat(
                        "sabbir",
                        "Rabin", "Okay! Bye",
                        Date().time,
                        0
                    )
                )

                chatList.add(
                    Chat(
                        "sabbir",
                        "Milon", "Okay! See you soon.",
                        Date().time,
                        1
                    )
                )

                chatList.add(
                    Chat(
                        "sabbir",
                        "Ahsan", "We'll meet tomorrow!",
                        Date().time,
                        1
                    )
                )
            }
            "Friends"->{
                chatList.add(
                    Chat(
                        "sabbir",
                        "Rabin", "Okay! Bye",
                        Date().time,
                        0
                    )
                )

                chatList.add(
                    Chat(
                        "sabbir",
                        "Milon", "Okay! See you soon.",
                        Date().time,
                        1
                    )
                )
            }
            "Work"->{
                chatList.add(
                    Chat(
                        "sabbir",
                        "Ahsan", "We'll meet tomorrow!",
                        Date().time,
                        1
                    )
                )
            }
        }

        chatListAdapter.notifyDataSetChanged()
    }
}
