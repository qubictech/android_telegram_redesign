package com.tarms.bd.messagingapp.data

import android.annotation.SuppressLint
import android.os.Parcelable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.tarms.bd.messagingapp.R
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class Chat(
    var imgUrl: String,
    var name: String,
    val msg: String,
    val timestamp: Long,
    val unreadMsgCount: Int
):Parcelable {

    companion object {
        @JvmStatic
        @BindingAdapter("android:setImageUrl")
        fun avatar(imageView: ImageView, imgUrl: String) {
            Glide.with(imageView.rootView)
                .load(imgUrl)
                .error(R.drawable.ic_person_outline)
                .circleCrop()
                .into(imageView)
        }

        @JvmStatic
        @SuppressLint("SimpleDateFormat")
        @BindingAdapter("android:timestampFormat")
        fun dateTimeFormat(textView: TextView, timestamp: Long) {

            val f = SimpleDateFormat("dd MMM yyyy")

            val formatter = if (f.format(Date(timestamp)) == f.format(Date().time)) {
                SimpleDateFormat("hh:mm a")
            } else SimpleDateFormat("dd MMM")

            val formattedDate = formatter.format(Date(timestamp))

            textView.text = (formattedDate)
        }
    }
}