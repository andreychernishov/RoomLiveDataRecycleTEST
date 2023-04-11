package com.example.roomlivedatarecycletest

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "word_table")
data class Word(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    @ColumnInfo(name = "word") val word: String?,
    @ColumnInfo(name = "money") val money: Float?,
    @ColumnInfo(name = "history") val history: String?
): Serializable