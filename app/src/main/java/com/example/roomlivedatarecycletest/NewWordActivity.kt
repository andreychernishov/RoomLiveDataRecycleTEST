package com.example.roomlivedatarecycletest

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class NewWordActivity : AppCompatActivity() {

    private lateinit var editWordView: EditText
    private lateinit var editWordView1: EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        editWordView = findViewById(R.id.edit_word)
        editWordView1 = findViewById(R.id.edit_word1)
        button = findViewById(R.id.button_save)

        button.setOnClickListener {
            val replyIntent = Intent()
            if(TextUtils.isEmpty(editWordView.text) || TextUtils.isEmpty(editWordView1.text)){
                Toast.makeText(
                    applicationContext,
                    "Enter the fields",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else{
                val word = editWordView.text.toString()
                val description = editWordView1.text.toString()
                replyIntent.putExtra(EXTRA_REPLY,word)
                replyIntent.putExtra(EXTRA_REPLY1,description)
                setResult(RESULT_OK,replyIntent)
                finish()
            }
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
        const val EXTRA_REPLY1 = "com.example.android.wordlistsql.REPLY1"
    }

}