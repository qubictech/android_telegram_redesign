package com.tarms.bd.messagingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tarms.bd.messagingapp.data.Contact
import com.tarms.bd.messagingapp.databinding.ContactMutualListBinding
import com.tarms.bd.messagingapp.utils.ContactItemClickEventListener
import java.util.logging.Logger

class MutualContactListAdapter(private val list: List<Contact>) :
    RecyclerView.Adapter<MutualContactListAdapter.ContactHolder>() {

    class ContactHolder(private val binding: ContactMutualListBinding) :
        RecyclerView.ViewHolder(binding.root), ContactItemClickEventListener {

        fun data(contact: Contact) {
            binding.contact = contact
            binding.apply {
                clickEvent = this@ContactHolder
            }
        }

        override fun onItemClick(view: View, contact: Contact) {
            Logger.getLogger("ContactHolder").warning("------------------> Clicked!")
        }
    }

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