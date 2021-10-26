package com.example.currencylistdemo.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.currencylistdemo.database.DatabaseUtils.Companion.KEY_NAME
import com.example.currencylistdemo.database.DatabaseUtils.Companion.KEY_SYMBOL

@Entity(tableName = "currency_table")
data class Currency(@PrimaryKey(autoGenerate = false) var id: String,
    @NonNull
    @ColumnInfo(name= KEY_NAME) var name: String,
    @NonNull
    @ColumnInfo(name= KEY_SYMBOL) var symbol: String
)