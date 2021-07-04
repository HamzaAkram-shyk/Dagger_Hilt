package com.example.hiltdi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.Util.Resource
import com.example.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.StringBuilder
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    lateinit var loading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loading = findViewById(R.id.loading)
        viewModel.set()
        viewModel.getObserver().observe(this, Observer {
            when (it) {
                is Resource.Success -> {
                    loading.visibility = View.GONE
                    val s = StringBuilder()
                    for (blog in it.data) {
                        s.append(blog.title + "\n")
                    }
                    findViewById<TextView>(R.id.textview).text = s.toString()
                }
                is Resource.Loading -> {
                    loading.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    loading.visibility = View.GONE
                    Toast.makeText(this, "${it.exception.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        })


    }
}