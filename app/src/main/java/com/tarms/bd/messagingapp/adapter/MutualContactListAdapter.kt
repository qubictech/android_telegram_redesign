package com.tarms.bd.messagingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tarms.bd.messagingapp.data.Contact
import com.tarms.bd.messagingapp.databinding.ContactMutualListBinding

class MutualContactListAdapter(private val list: List<Contact>) :
    RecyclerView.Adapter<ContactHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ContactMutualListBinding.inflate(inflater, parent, false)

        return ContactHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.data(list[position])
    }
}