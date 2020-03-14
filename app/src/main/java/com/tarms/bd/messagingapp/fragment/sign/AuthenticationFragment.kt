package com.tarms.bd.messagingapp.fragment.sign


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.tarms.bd.messagingapp.R
import com.tarms.bd.messagingapp.main.MainActivity
import com.tarms.bd.messagingapp.main.UserSignActivity
import com.tarms.bd.messagingapp.utils.FirebaseUtil
import java.util.concurrent.TimeUnit
import java.util.logging.Logger

private const val PHONE_NUMBER = "com.tarms.bd.messagingapp.fragment.sign.phone"

class AuthenticationFragment : Fragment(R.layout.fragment_authentication) {
    private lateinit var auth: FirebaseAuth
    private var phoneNumber: String? = null

    private lateinit var p1: EditText
    private lateinit var p2: EditText
    private lateinit var p3: EditText
    private lateinit var p4: EditText
    private lateinit var p5: EditText
    private lateinit var p6: EditText

    private var verificationId: String? = ""

    private var mVerificationCode = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            phoneNumber = it.getString(PHONE_NUMBER)
            startPhoneNumberAuthentication("+88$phoneNumber")
        }
    }

    companion object {
        const val TAG = "AuthenticationFragment"
        @JvmStatic
        fun newInstance(phone_number: String) =
            AuthenticationFragment().apply {
                arguments = Bundle().apply {
                    putString(PHONE_NUMBER, phone_number)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        view.findViewById<MaterialButton>(R.id.continue_btn)
            .setOnClickListener {
                if (mVerificationCode.length == 6) verifyPhoneNumber(
                    verificationId,
                    mVerificationCode
                )
            }

        p1 = view.findViewById(R.id.p1)
        p1.addTextChangedListener(GenericTextWatcher(p1))
        p1.requestFocus()

        p2 = view.findViewById(R.id.p2)
        p2.addTextChangedListener(GenericTextWatcher(p2))

        p3 = view.findViewById(R.id.p3)
        p3.addTextChangedListener(GenericTextWatcher(p3))

        p4 = view.findViewById(R.id.p4)
        p4.addTextChangedListener(GenericTextWatcher(p4))

        p5 = view.findViewById(R.id.p5)
        p5.addTextChangedListener(GenericTextWatcher(p5))

        p6 = view.findViewById(R.id.p6)
        p1.addTextChangedListener(GenericTextWatcher(p6))
    }

    private fun startPhoneNumberAuthentication(phoneNumber: String) {
        activity?.let { fragment ->
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                fragment,
                object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                        signInWithPhoneAuthCredential(p0)
                    }

                    override fun onVerificationFailed(p0: FirebaseException) {
                        p0.printStackTrace()
                    }

                    override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                        super.onCodeSent(p0, p1)
                        verificationId = p0

                        Toast.makeText(context, "Code Sent", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }

    private fun verifyPhoneNumber(verificationId: String?, code: String) {
        try {
            val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
            signInWithPhoneAuthCredential(credential)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")

                    Logger.getLogger(TAG).warning("signInWithCredential:success")
                    val user = task.result?.user

                    if (user != null)
                        FirebaseUtil.isExistedUser { available ->
                            Logger.getLogger(TAG).warning("user activity $available")

                            if (available) {
                                startActivity(Intent(context, MainActivity::class.java))
                                activity!!.finish()
                            } else {
                                val userSignActivity: UserSignActivity =
                                    activity as UserSignActivity
                                userSignActivity.changeFragment(SignUpFragment())
                            }
                        }
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Logger.getLogger(TAG)
                        .warning("signInWithCredential:failure ${task.exception?.message}")

                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(context, "Enter a valid code", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            }
    }

    inner class GenericTextWatcher(private val view: View) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            val string = s.toString()
            var c1 = ""
            var c2 = ""
            var c3 = ""
            var c4 = ""
            var c5 = ""
            var c6 = ""

            when (view.id) {
                R.id.p1 -> if (string.length == 1) {
                    p2.requestFocus()
                    c1 = string
                }

                R.id.p2 -> if (string.length == 1) {
                    p3.requestFocus()
                    c2 = string
                } else if (string.isEmpty()) p1.requestFocus()

                R.id.p3 -> if (string.length == 1) {
                    p4.requestFocus()
                    c3 = string
                } else if (string.isEmpty()) p2.requestFocus()

                R.id.p4 -> if (string.length == 1) {
                    p5.requestFocus()
                    c4 = string
                } else if (string.isEmpty()) p3.requestFocus()

                R.id.p5 -> if (string.length == 1) {
                    p6.requestFocus()
                    c5 = string
                } else if (string.isEmpty()) p4.requestFocus()

                R.id.p6 -> if (string.length == 1) {
                    p6.clearFocus()
                    c6 = string
                }
            }

            mVerificationCode = c1 + c2 + c3 + c4 + c5 + c6
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

    }
}
