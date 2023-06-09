package com.example.roomlivedatarecycletest

import androidx.annotation.WorkerThread
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow


// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO

class WordRepository(private val wordDao: WordDao): ViewModel() {
    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.

    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word){
        wordDao.insert(word)
    }
    suspend fun update(word: Word){
        wordDao.update(word)
    }
    suspend fun deleteItem(word: Word){
        wordDao.delete(word)
    }
    suspend fun deleteAll(){
        wordDao.deleteAll()
    }
}