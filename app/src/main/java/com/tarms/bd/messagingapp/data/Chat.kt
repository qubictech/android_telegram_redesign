package com.tarms.bd.messagingapp.data

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.tarms.bd.messagingapp.R

data class Chat(
    var imgUrl: String,
    var name: String,
    val msg: String,
    val timestamp: Long,
    val unreadMsgCount: Int
) {
    @BindingAdapter("android:setImageUrl")
    fun avatar(imageView: ImageView, imgUrl: String) {
        Glide.with(imageView.rootView)
            .load(imgUrl)
            .error(R.drawable.ic_person_outline)
            .circleCrop()
            .into(imageView)
    }
}