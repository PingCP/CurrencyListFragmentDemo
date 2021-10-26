package com.example.currencylistdemo

import android.app.Application
import com.example.currencylistdemo.database.CurrencyRepository
import com.example.currencylistdemo.database.CurrencyRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CurrencyApplication: Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { CurrencyRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { CurrencyRepository(database.getCurrencyDao()) }
}