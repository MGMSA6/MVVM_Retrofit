package com.example.retrofitmvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitmvvm.models.QuoteList
import com.example.retrofitmvvm.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            quoteRepository.getQuotes(1)
        }
    }

    val quotes: LiveData<QuoteList>
    get() = quoteRepository.quotes
}