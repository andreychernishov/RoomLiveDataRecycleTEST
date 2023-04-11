package com.example.roomlivedatarecycletest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class HistoryListAdapter: ListAdapter<Word, HistoryListAdapter.HistoryViewHolder>(WordsComparator()) {

    class HistoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val historyTv: TextView = itemView.findViewById(R.id.history_tv)

        fun bind(text: String?){
            historyTv.text = text
        }
        companion object {
            fun create(parent: ViewGroup): HistoryViewHolder{
                val view: View = LayoutInflater.from(parent.context).inflate(R.layout.history_recyclerview_item,parent,false)
                return HistoryViewHolder(view)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.history)
    }
}