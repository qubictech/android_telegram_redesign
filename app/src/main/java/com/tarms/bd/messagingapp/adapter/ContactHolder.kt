package com.tarms.bd.messagingapp.adapter

import androidx.recyclerview.widget.RecyclerView
import com.tarms.bd.messagingapp.data.Contact
import com.tarms.bd.messagingapp.databinding.ContactMutualListBinding

class ContactHolder(private val binding: ContactMutualListBinding) : RecyclerView.ViewHolder(binding.root) {

    fun data(contact: Contact) {
        binding.contact = contact
    }
}