package com.tarms.bd.messagingapp.fragment.authentication


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.tarms.bd.messagingapp.R
import com.tarms.bd.messagingapp.main.MainActivity
import com.tarms.bd.messagingapp.main.UserAuthActivity

/**
 * A simple [Fragment] subclass.
 */
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            startActivity(Intent(context, MainActivity::class.java))
            activity?.finish()
        }

        view.findViewById<MaterialButton>(R.id.continue_btn)
            .setOnClickListener {
                val mEditTextPhone = view.findViewById<TextInputLayout>(R.id.phone_number)
                val phone = mEditTextPhone.editText?.text.toString()

                if (isValidPhoneNumber(phone, mEditTextPhone)) {
                    openAuthenticationFragment(phone)
                }
            }
    }

    private fun isValidPhoneNumber(phone: String, mPhoneInputLayout: TextInputLayout): Boolean {
        val valid: Boolean

        when {
            phone.isEmpty() -> {
                mPhoneInputLayout.error = "Contact number can't be empty"
                valid = false
            }

            phone.length == 11 -> {
                valid = true
                mPhoneInputLayout.isErrorEnabled = false
            }

            else -> {
                valid = false
                mPhoneInputLayout.error = "Enter a valid contact number"
            }
        }
        return valid
    }

    private fun openAuthenticationFragment(phone: String) {
        val userAuthActivity: UserAuthActivity = activity as UserAuthActivity
        userAuthActivity.changeFragment(AuthenticationFragment.newInstance(phone_number = phone))
    }
}
