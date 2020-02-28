package com.tarms.bd.messagingapp.fragment.tabs


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tarms.bd.messagingapp.R
import com.tarms.bd.messagingapp.adapter.ChatListAdapter
import com.tarms.bd.messagingapp.data.Chat
import com.tarms.bd.messagingapp.repository.MyRepository
import com.tarms.bd.messagingapp.repository.MyViewModel
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_tab, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val v = ViewModelProvider(this).get(MyViewModel::class.java)

        arguments?.let { bundle ->
            v.getChatList(bundle.getString(ARGS).toString()).observe(this, Observer { list ->
                val chatListAdapter = context?.let { context ->
                    ChatListAdapter(context, list)
                }!!

                view.findViewById<RecyclerView>(R.id.recycler_view).apply {
                    adapter = chatListAdapter
                    hasFixedSize()
                    layoutManager = LinearLayoutManager(context)
                }
            })
        }
    }
}