package com.example.movierecyclerview.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movierecyclerview.R
import com.example.movierecyclerview.databinding.ActivityMainBinding
import com.example.movierecyclerview.model.adapters.MyAdapter
import com.example.movierecyclerview.model.datasource.remote.retrofit.CONSTANTS.Companion.API_KEY
import com.example.movierecyclerview.model.datasource.remote.retrofit.MovieService
import com.example.movierecyclerview.model.moviedataclasses.PopResults
import com.example.movierecyclerview.view.custom.CustomOnScrollListener
import com.example.movierecyclerview.viewmodel.MainActivityViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private lateinit var viewManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.RVlist)

        recyclerView.layoutManager = viewManager
        recyclerView.addOnScrollListener(object :CustomOnScrollListener(viewManager){
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                Toast.makeText(applicationContext,"Would load more page " + page, Toast.LENGTH_LONG).show()

                viewModel.MovieCall(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { addData(it) }
            }
        })

        //viewModel.standardCall(recyclerView)
        viewModel.MovieCall(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { populateData(it) }
    }
    private fun populateData(dataSet: PopResults?) {
        dataSet?.let {
            adapter = MyAdapter(it.results!!)
            recyclerView.adapter = adapter
        }
    }

    private fun addData(dataSet: PopResults?) {
        dataSet?.let {
            adapter.addList(dataSet.results!!)
        }
    }
}
