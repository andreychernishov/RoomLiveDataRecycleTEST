package com.example.roomlivedatarecycletest

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class Word(@PrimaryKey
    @ColumnInfo(name = "word") val word: String // добавить колонку і зробить міграцію
    //
)