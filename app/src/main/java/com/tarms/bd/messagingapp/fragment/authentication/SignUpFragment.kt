package com.tarms.bd.messagingapp.fragment.authentication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.tarms.bd.messagingapp.R
import com.tarms.bd.messagingapp.data.User
import com.tarms.bd.messagingapp.main.MainActivity
import com.tarms.bd.messagingapp.repository.FirebaseUtil

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<MaterialButton>(R.id.finish_btn)
            .setOnClickListener {
                val mName = view.findViewById<TextInputLayout>(R.id.full_name)
                if (mName.editText?.text.toString().isNotEmpty())
                    FirebaseUtil.createNewUser(User(mName.editText?.text.toString(), "")).let {
                        startActivity(Intent(context, MainActivity::class.java))
                        activity?.finish()
                    }
            }
    }
}
