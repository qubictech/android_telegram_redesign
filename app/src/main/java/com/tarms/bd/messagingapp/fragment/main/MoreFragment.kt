package com.tarms.bd.messagingapp.fragment.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.tarms.bd.messagingapp.BuildConfig
import com.tarms.bd.messagingapp.R
import com.tarms.bd.messagingapp.data.User
import com.tarms.bd.messagingapp.databinding.FragmentMoreBinding
import com.tarms.bd.messagingapp.main.UserAuthActivity
import com.tarms.bd.messagingapp.repository.FirebaseUtil
import com.tarms.bd.messagingapp.utils.OnViewItemClickListener
import java.util.logging.Logger

class MoreFragment : Fragment(), OnViewItemClickListener {
    private lateinit var binding: FragmentMoreBinding
    private val mUser = FirebaseAuth.getInstance().currentUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_more,
            container,
            false
        )
        binding.version = "Version ${BuildConfig.VERSION_NAME}"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirebaseUtil.getOrCreateUser {
            if (!it) {
                startActivity(Intent(context, UserAuthActivity::class.java))
                activity!!.finish()
            }
        }

        binding.clickEvent = this

        binding.user = User(
            name = mUser!!.displayName.toString(),
            imgUrl = mUser.photoUrl.toString()
        )
    }

    override fun onViewClicked(view: View) {
        Logger.getLogger(TAG).warning("onViewClicked: true")

        when (view.id) {
            R.id.card_logout -> {
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(context, UserAuthActivity::class.java))
                activity?.finish()
            }
        }
    }

    companion object {
        const val TAG = "MoreFragment"
    }
}
