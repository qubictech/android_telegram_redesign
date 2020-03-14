package com.tarms.bd.messagingapp.fragment.sign

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.tarms.bd.messagingapp.R
import com.tarms.bd.messagingapp.data.User
import com.tarms.bd.messagingapp.main.MainActivity
import com.tarms.bd.messagingapp.utils.FirebaseUtil

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

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

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUpFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
