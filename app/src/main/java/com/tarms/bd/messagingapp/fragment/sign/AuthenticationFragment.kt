package com.tarms.bd.messagingapp.fragment.sign


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.tarms.bd.messagingapp.R
import com.tarms.bd.messagingapp.main.UserSignActivity

private const val PHONE_NUMBER = "com.tarms.bd.messagingapp.fragment.sign.phone"

class AuthenticationFragment : Fragment() {
    private var phoneNumber: String? = null

    private lateinit var p1: EditText
    private lateinit var p2: EditText
    private lateinit var p3: EditText
    private lateinit var p4: EditText
    private lateinit var p5: EditText
    private lateinit var p6: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            phoneNumber = it.getString(PHONE_NUMBER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_authentication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<MaterialButton>(R.id.continue_btn)
            .setOnClickListener {
                val userSignActivity: UserSignActivity = activity as UserSignActivity
                userSignActivity.changeFragment(SignUpFragment())
            }

        p1 = view.findViewById(R.id.p1)
        p1.addTextChangedListener(GenericTextWatcher(p1))

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

    companion object {
        @JvmStatic
        fun newInstance(phone_number: String) =
            AuthenticationFragment().apply {
                arguments = Bundle().apply {
                    putString(PHONE_NUMBER, phone_number)
                }
            }
    }

    inner class GenericTextWatcher(private val view: View) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            val string = s.toString()

            when (view.id) {
                R.id.p1 -> if (string.length == 1) {
                    p2.requestFocus()
                }

                R.id.p2 -> if (string.length == 1) {
                    p3.requestFocus()
                } else if (string.isEmpty()) p1.requestFocus()

                R.id.p3 -> if (string.length == 1) {
                    p4.requestFocus()
                } else if (string.isEmpty()) p2.requestFocus()

                R.id.p4 -> if (string.length == 1) {
                    p5.requestFocus()
                } else if (string.isEmpty()) p3.requestFocus()

                R.id.p5 -> if (string.length == 1) {
                    p6.requestFocus()
                } else if (string.isEmpty()) p4.requestFocus()

                R.id.p6 -> if (string.length == 1) p6.clearFocus()
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

    }
}
