package com.example.movierecyclerview.model.datasource.remote.retrofit

import com.example.movierecyclerview.model.moviedataclasses.PopResults
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    companion object {
        fun createService() : MovieService =
            RetrofitHelper().getRetrofitInstance().create(MovieService::class.java)
    }

    @GET("3/discover/movie/")
    fun getMovies(@Query("sort_by") sortPerm: String,
                  @Query("api_key") api_key : String,
                  @Query("page") pageNum : String)
            : Observable<PopResults>
}