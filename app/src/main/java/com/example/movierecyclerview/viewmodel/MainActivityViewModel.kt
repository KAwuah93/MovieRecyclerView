package com.example.movierecyclerview.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.movierecyclerview.model.adapters.MyAdapter
import com.example.movierecyclerview.model.datasource.remote.retrofit.CONSTANTS
import com.example.movierecyclerview.model.datasource.remote.retrofit.MovieService
import com.example.movierecyclerview.model.moviedataclasses.PopResults
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel : ViewModel() {

    //Tags
    val TAG = "NETCALL"

    fun standardCall(recyclerView: RecyclerView){
        Log.d(TAG, " 1 Stepping into the method.. ")
        MovieService.createService()
            .getMovies("popularity.desc", CONSTANTS.API_KEY,"1")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                object : Observer<PopResults> {
                    lateinit var popResults: PopResults
                    override fun onComplete() {
                        //TODO do the call and log the results
                        //Log.d(TAG, "Object = " + popResults.results!![0])
                        recyclerView.adapter = MyAdapter(popResults.results!!)
                    }
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(t: PopResults) {
                        //Log.d(TAG, " 3 Stepping into the method.. ")
                        this.popResults = t
                    }
                    override fun onError(e: Throwable) {}
                }
            )
    }
}