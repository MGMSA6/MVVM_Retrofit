package com.example.retrofitmvvm

import android.app.Application
import com.example.retrofitmvvm.api.QuoteService
import com.example.retrofitmvvm.api.RetrofitHelper
import com.example.retrofitmvvm.db.QuoteDatabase
import com.example.retrofitmvvm.repository.QuoteRepository

class QuoteApplication : Application() {

    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteService, database, applicationContext)
    }
}