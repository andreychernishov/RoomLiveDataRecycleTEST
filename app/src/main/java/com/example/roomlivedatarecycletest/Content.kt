package com.example.roomlivedatarecycletest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import java.io.Serializable as Serializable1


lateinit var changeInfo: Button
lateinit var contentTv: TextView
lateinit var contentTv1: TextView
class Content : AppCompatActivity(),Serializable1{
    var isClicked: Boolean = false
    private var launcher: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        val item = intent.getSerializableExtra("example_key") as Word?
        contentTv = findViewById(R.id.content_word)
        contentTv1 = findViewById(R.id.content_description)
        changeInfo = findViewById(R.id.change_bank_name_btn)

        if (isClicked == false){
            startUpdate(item!!)
            contentTv.text = item.word
            contentTv1.text = item.description
        }else{
            updateContent()
        }
    }

   private fun startUpdate(item: Word){
        changeInfo.setOnClickListener {
            startActivity(Intent(this, UpdateActivity::class.java).apply {
                putExtra("example_key1", item)
            })
        }
    }
    private fun updateContent(){
        val updatedItem = intent.getSerializableExtra("key111") as Word
        contentTv.text = updatedItem.word
        contentTv1.text = updatedItem.description
    }
}