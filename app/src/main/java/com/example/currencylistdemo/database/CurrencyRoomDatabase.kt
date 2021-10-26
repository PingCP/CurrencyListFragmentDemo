package com.example.currencylistdemo.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Currency::class], version = 2)
abstract class CurrencyRoomDatabase: RoomDatabase() {
    abstract fun getCurrencyDao(): CurrencyDao

    private class CurrencyDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        private val TAG = "Apple_" + CurrencyDatabaseCallback::class.java.simpleName

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            Log.d(TAG, "onCreate")
            INSTANCE?.let { database ->
                scope.launch {
                    var currencyDao = database.getCurrencyDao()

                    // Delete all content here.
                    currencyDao.deleteAll()

                    Log.d(TAG, "CurrencyDatabaseCallback")
                    var word = Currency("BTC", "Bitcoin", "BTC")
                    currencyDao.insert(word)
                    word = Currency("ETH", "Ethereum", "ETH")
                    currencyDao.insert(word)
                    word = Currency("XRP", "XRP", "XRP")
                    currencyDao.insert(word)
                    word = Currency("BCH", "Bitcoin Cash", "BCH")
                    currencyDao.insert(word)
                    word = Currency("LTC", "Litecoin", "LTC")
                    currencyDao.insert(word)
                    word = Currency("EOS", "EOS", "EOS")
                    currencyDao.insert(word)
                    word = Currency("BNB", "Binance Coin", "BNB")
                    currencyDao.insert(word)
                    word = Currency("LINK", "Chianlink", "LINK")
                    currencyDao.insert(word)
                    word = Currency("NEO", "NEO", "NEO")
                    currencyDao.insert(word)
                    word = Currency("ETC", "Ethereum Classic", "ETC")
                    currencyDao.insert(word)
                    word = Currency("ONT", "Ontology", "ONT")
                    currencyDao.insert(word)
                    word = Currency("CRO", "Crypto.com Chain", "CRO")
                    currencyDao.insert(word)
                    word = Currency("CUC", "Cucumber", "CUC")
                    currencyDao.insert(word)
                    word = Currency("USDC", "USD Coin", "USDC")
                    currencyDao.insert(word)

                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CurrencyRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CurrencyRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    CurrencyRoomDatabase::class.java,
                    DATABASE_NAME
                ).addCallback(CurrencyDatabaseCallback(scope)).build()
                INSTANCE = instance
                instance
            }
        }
    }
}