package com.example.retrofitmvvm

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitmvvm.api.QuoteServices
import com.example.retrofitmvvm.api.RetrofitHelper
import com.example.retrofitmvvm.repository.QuoteRepository
import com.example.retrofitmvvm.viewmodels.MainViewModel
import com.example.retrofitmvvm.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quoteServices = RetrofitHelper.getInstance().create(QuoteServices::class.java)

        val quoteRepository = QuoteRepository(quoteServices)

        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(quoteRepository)
        )[MainViewModel::class.java]

        mainViewModel.quotes.observe(this, Observer {
            Log.d("TAG", it.results.toString())
        })

    }
}