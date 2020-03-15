package com.tarms.bd.messagingapp.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.tarms.bd.messagingapp.R
import com.tarms.bd.messagingapp.data.Chat
import com.tarms.bd.messagingapp.fragment.attachment.AttachmentBottomSheetFragment

class ChatActivity : AppCompatActivity(R.layout.activity_chat) {

    private lateinit var sendTextMessageBtn: ImageButton
    private lateinit var sendVoiceMessageBtn: ImageButton
    private lateinit var attachFiles: ImageButton

    private lateinit var messageEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val chat = intent.getParcelableExtra<Chat>("chat")

        chat?.let {
            supportActionBar?.title = it.name
        }

        findViewById<RecyclerView>(R.id.chats).apply {

        }

        messageEditText = findViewById(R.id.message)
        sendTextMessageBtn = findViewById(R.id.send_text_msg)
        sendVoiceMessageBtn = findViewById(R.id.voice_msg)
        attachFiles = findViewById(R.id.attachment)

        attachFiles.setOnClickListener {
            val attachmentBottomSheetFragment = AttachmentBottomSheetFragment()
            attachmentBottomSheetFragment.show(supportFragmentManager, "attachment")
        }

        sendTextMessageBtn.setOnClickListener {
            messageEditText.text = null
        }

        messageEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    sendVoiceMessageBtn.visibility = View.VISIBLE
                    sendTextMessageBtn.visibility = View.GONE
                } else if (s.toString().length == 1) {
                    sendVoiceMessageBtn.visibility = View.GONE
                    sendTextMessageBtn.visibility = View.VISIBLE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.chat, menu)

        if (menu is MenuBuilder) {
            menu.setOptionalIconsVisible(true)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()

        return super.onOptionsItemSelected(item)
    }
}
