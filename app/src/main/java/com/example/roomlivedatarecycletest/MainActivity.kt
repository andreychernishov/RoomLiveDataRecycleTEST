package com.example.roomlivedatarecycletest

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.Serializable as Serializable1

class MainActivity : AppCompatActivity(), WordListAdapter.Listener, Serializable1 {
    private val wordViewModel: WordViewModel by viewModels{
        WordViewModelFactory((application as WordsApplication).repository)
    }
    private var launcher: ActivityResultLauncher<Intent>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        wordViewModel.allWords.observe(this){ words ->
            words.let {
                adapter.submitList(it)
            }
        }

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener{
            launcher?.launch(Intent(this, NewWordActivity::class.java))
        }

        launcher = registerForActivityResult(ActivityResultContracts
            .StartActivityForResult()){
                result: ActivityResult ->
            if (result.resultCode == RESULT_OK){
                result.data?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let { reply ->
                    var word: Word
                    result.data?.getStringExtra(NewWordActivity.EXTRA_REPLY1)?.let { reply1 ->
                        word = Word(null,reply,reply1.toFloat(),null)
                        wordViewModel.insert(word)
                    }
                }
            }else {
                Toast.makeText(
                    applicationContext,
                    "www",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun itemOnClick(item: Word) {
        startActivity(Intent(this, Content::class.java).apply {
            putExtra("example_key",item)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_del -> {
                deleteAll()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAll() {
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("Yes"){_, _->
            wordViewModel.deleteAll()
        }
        builder.setNegativeButton("No"){_, _ ->}
        builder.setTitle("Delete all items ?")
        builder.setMessage("Are you sure to delete all items?")
        builder.create().show()
    }

}