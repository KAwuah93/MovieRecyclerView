package com.example.movierecyclerview.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.movierecyclerview.model.adapters.MyAdapter
import com.example.movierecyclerview.model.datasource.remote.retrofit.CONSTANTS
import com.example.movierecyclerview.model.datasource.remote.retrofit.MovieService
import com.example.movierecyclerview.model.moviedataclasses.PopResults
import io.reactivex.Flowable.merge
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel : ViewModel() {

    //Tags
    val TAG = "NETCALL"

    fun MovieCall(page: Int): Observable<PopResults> {

        return MovieService
            .createService()
            .getMovies("popularity.desc", CONSTANTS.API_KEY,page.toString())
    }

    fun additionalCall(): Observable<PopResults>{

        return MovieService.createService()
            .getMovies("popularity.desc", CONSTANTS.API_KEY,"2")
    }
}