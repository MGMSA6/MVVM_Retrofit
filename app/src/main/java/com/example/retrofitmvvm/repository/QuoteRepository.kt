package com.example.retrofitmvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofitmvvm.api.QuoteServices
import com.example.retrofitmvvm.models.QuoteList

class QuoteRepository(private val quoteServices: QuoteServices) {

    private val quotesLiveData = MutableLiveData<QuoteList>()

    val quotes: LiveData<QuoteList>
        get() = quotesLiveData

    suspend fun getQuotes(page: Int) {
        val result = quoteServices.getQuotes(page)

        if (result?.body() != null) {
            quotesLiveData.postValue(result.body())
        }
    }

}