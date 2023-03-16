package com.example.roomlivedatarecycletest

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewWordActivity : AppCompatActivity() {

    private lateinit var editWordView: EditText
    private lateinit var editWordView1: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        editWordView = findViewById(R.id.edit_word)

        val button: Button = findViewById(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            val replyIntent1 = Intent()
            if(TextUtils.isEmpty(editWordView.text)){
                setResult(Activity.RESULT_CANCELED,replyIntent)
            }else{
                val word = editWordView.text.toString()
                val word1 = editWordView1.text.toString()
                replyIntent.putExtra(EXTRA_REPLY,word)
                replyIntent1.putExtra(EXTRA_REPLY,word1)
                setResult(Activity.RESULT_OK,replyIntent)
                setResult(Activity.RESULT_OK,replyIntent1)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }

}