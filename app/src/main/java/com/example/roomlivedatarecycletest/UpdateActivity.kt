package com.example.roomlivedatarecycletest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import java.io.Serializable as Serializable1

lateinit var updateBankName: EditText
lateinit var updateMoney: EditText
lateinit var saveUpdate: Button
class UpdateActivity : AppCompatActivity(), Serializable1 {
    private val wordViewModel: WordViewModel by viewModels{
        WordViewModelFactory((application as WordsApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val item = intent.getSerializableExtra("example_key1") as Word

        saveUpdate = findViewById(R.id.save_update)
        updateBankName = findViewById(R.id.update_word)
        updateMoney= findViewById(R.id.update_word1)

        updateBankName.setText(item.word)
        updateMoney.setText(item.money.toString())

        saveUpdate.setOnClickListener {
            val itemId = item.id
            val bankName = updateBankName.text.toString()
            val money = updateMoney.text.toString()
            val history = "${item.money} to ${updateMoney.text}"
            finish().apply {
                startActivity(Intent(this@UpdateActivity,MainActivity::class.java).apply {
                    putExtra("key111",updateWord(itemId!!,bankName, money.toFloat(), history))
                })
            }
        }
    }
    private fun updateWord(id: Long, bankName: String, money: Float, history: String): Word{
        val updateInfo = Word(id,bankName,money,history)
        wordViewModel.update(updateInfo)
        return updateInfo
        }
    }