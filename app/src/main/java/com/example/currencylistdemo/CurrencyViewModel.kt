package com.example.currencylistdemo

import androidx.lifecycle.*
import com.example.currencylistdemo.database.Currency
import com.example.currencylistdemo.database.CurrencyRepository
import kotlinx.coroutines.launch

class CurrencyViewModel(private val repository: CurrencyRepository) : ViewModel() {
    val allCurrency: LiveData<List<Currency>> = repository.allCurrency.asLiveData()

    fun insert(currency: Currency) = viewModelScope.launch {
        repository.insert(currency)
    }
}

class CurrencyViewModelFactory(private val repository: CurrencyRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrencyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CurrencyViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
