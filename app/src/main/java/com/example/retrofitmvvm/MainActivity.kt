package com.example.retrofitmvvm

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitmvvm.repository.Response
import com.example.retrofitmvvm.viewmodels.MainViewModel
import com.example.retrofitmvvm.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = (application as QuoteApplication).quoteRepository

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.quotes.observe(this, Observer {
            when (it) {
                is Response.Loading -> {
                    Toast.makeText(
                        this@MainActivity,
                       "Loading",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is Response.Success -> {
                    it.data?.let {
                        Toast.makeText(
                            this@MainActivity,
                            it.results.size.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                is Response.Error -> {
                    it.errorMessage
                        Toast.makeText(
                            this@MainActivity,
                            "Some error occured",
                            Toast.LENGTH_SHORT
                        ).show()
                }
            }
        })
    }
}