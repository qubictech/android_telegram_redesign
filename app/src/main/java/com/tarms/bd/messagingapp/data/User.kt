package com.tarms.bd.messagingapp.data

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.tarms.bd.messagingapp.R

data class User(val name:String,val imgUrl:String) {
    companion object{
        @JvmStatic
        @BindingAdapter("android:setImageUrl")
        fun avatar(imageView: ImageView, imgUrl: String) {
            Glide.with(imageView.rootView)
                .load(imgUrl)
                .error(R.drawable.ic_person_outline)
                .circleCrop()
                .into(imageView)
        }
    }
}