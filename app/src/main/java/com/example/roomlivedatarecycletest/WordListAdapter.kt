package com.example.roomlivedatarecycletest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class WordListAdapter: ListAdapter<Word, WordListAdapter.WordViewHolder>(WordsComparator()) {


    class WordViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val wordItemView: TextView = itemView.findViewById(R.id.textView)
        private val wordItemView1: TextView = itemView.findViewById(R.id.textView1)

        fun bind (text: String,text1: String){
            wordItemView.text = text
            wordItemView1.text = text1
        }
        companion object{
            fun create(parent: ViewGroup): WordViewHolder{
                val view : View = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item,parent,false)
             return WordViewHolder(view)
            }
        }
    }

    class WordsComparator: DiffUtil.ItemCallback<Word>(){
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.word == newItem.word
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent)

    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.word,current.description)
    }


}