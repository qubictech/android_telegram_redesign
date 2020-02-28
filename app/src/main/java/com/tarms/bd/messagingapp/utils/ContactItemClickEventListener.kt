package com.tarms.bd.messagingapp.utils

import android.view.View
import com.tarms.bd.messagingapp.data.Contact

interface ContactItemClickEventListener {
    fun onItemClick(view: View,contact: Contact)
}