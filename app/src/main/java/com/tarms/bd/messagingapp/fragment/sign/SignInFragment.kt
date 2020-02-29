package com.tarms.bd.messagingapp.fragment.sign


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.tarms.bd.messagingapp.R
import com.tarms.bd.messagingapp.main.UserSignActivity

/**
 * A simple [Fragment] subclass.
 */
class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<MaterialButton>(R.id.continue_btn)
            .setOnClickListener {
                val userSignActivity: UserSignActivity = activity as UserSignActivity
                userSignActivity.changeFragment(AuthenticationFragment())
            }
    }

}
