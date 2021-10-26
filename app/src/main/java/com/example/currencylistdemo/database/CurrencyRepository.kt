package com.example.currencylistdemo.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class CurrencyRepository(private val currencyDao: CurrencyDao) {
    val allCurrency: Flow<List<Currency>> = currencyDao.getCurrencyList()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(currency: Currency) {
        currencyDao.insert(currency)
    }
}