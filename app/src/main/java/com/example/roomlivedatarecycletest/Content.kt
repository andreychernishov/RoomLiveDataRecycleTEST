package com.example.roomlivedatarecycletest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable as Serializable1


lateinit var changeInfo: Button
lateinit var contentTv: TextView
lateinit var contentTv1: TextView
private lateinit var itemWord : Word
class Content : AppCompatActivity(),Serializable1{
    private val wordViewModel: WordViewModel by viewModels{
        WordViewModelFactory((application as WordsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        recyclerInit()

        itemWord = intent.getSerializableExtra("example_key") as Word

        contentTv = findViewById(R.id.content_word)
        contentTv1 = findViewById(R.id.content_description)
        changeInfo = findViewById(R.id.change_bank_name_btn)

        contentTv.text = itemWord?.word
        contentTv1.text = itemWord?.money.toString()
        startUpdate(itemWord!!)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_del ->{
                deleteItem()
                Toast.makeText(this,"${itemWord.word} deleted", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteItem(){
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("Yes"){_, _->
            wordViewModel.delete(itemWord)
            finish()
        }
        builder.setNegativeButton("No"){_, _ ->}
            builder.setTitle("Delete ${itemWord.word} ?")
            builder.setMessage("Are you sure to delete ${itemWord.word}?")
            builder.create().show()

    }
    private fun startUpdate(item: Word){
        changeInfo.setOnClickListener {
            startActivity(Intent(this, UpdateActivity::class.java).apply {
                putExtra("example_key1", item)
            })
        }
    }
    private fun recyclerInit(){
        val recyclerView = findViewById<RecyclerView>(R.id.history_rc)
        val adapter = HistoryListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        wordViewModel.allWords.observe(this){ words->
            words.let {
                adapter.submitList(it)
            }
        }
    }
    private fun updateContent(){
        val updatedItem = intent.getSerializableExtra("key111") as Word
        contentTv.text = updatedItem.word
        contentTv1.text = updatedItem.money.toString()
    }
}