package com.example.movierecyclerview.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movierecyclerview.R
import com.example.movierecyclerview.databinding.ActivityMainBinding
import com.example.movierecyclerview.model.adapters.MyAdapter
import com.example.movierecyclerview.model.datasource.remote.retrofit.CONSTANTS.Companion.API_KEY
import com.example.movierecyclerview.model.datasource.remote.retrofit.MovieService
import com.example.movierecyclerview.model.moviedataclasses.PopResults
import com.example.movierecyclerview.viewmodel.MainActivityViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel
    lateinit var binding: ActivityMainBinding

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MyAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = MainActivityViewModel()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.RVlist)

        recyclerView.layoutManager = viewManager

        viewModel.standardCall(recyclerView)
    }
}
