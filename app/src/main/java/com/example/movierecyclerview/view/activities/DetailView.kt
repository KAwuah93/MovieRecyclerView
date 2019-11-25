package com.example.movierecyclerview.view.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.movierecyclerview.R
import com.example.movierecyclerview.model.datasource.remote.retrofit.CONSTANTS.Companion.IMG_BASEURL
import com.example.movierecyclerview.model.moviedataclasses.ResultsItem
import com.squareup.picasso.Picasso
import android.content.Intent
import android.content.res.Configuration


class DetailView : AppCompatActivity() {

    lateinit var id:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view)

        // Pull the extra and attach it to the info on the screen
        //intent.getExtra

        populate()
    }

    //This is where we'll 'populate' the page with the information
    fun populate(){
        var bundle = intent.extras
        var item = bundle?.get("movie") as ResultsItem

        id = item.id.toString()
        //populating the actual body
        findViewById<TextView>(R.id.tvDetailTitle).text = item.title
        findViewById<TextView>(R.id.tvDetailPopularity).text = "Popularity: " + item.popularity
        findViewById<TextView>(R.id.tvDetailVotes).text = "# of Votes: "+item.voteCount
        findViewById<TextView>(R.id.tvDetailScore).text = "Average: "+item.voteAverage
        findViewById<TextView>(R.id.tvDetailDesc).text = item.overview

        //video image
        val orientation = getResources().getConfiguration().orientation


        var picasso = Picasso.get()


        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            picasso.load(IMG_BASEURL + item.posterPath).into( findViewById<ImageView>(R.id.ivDetailImage) )
        } else {
            picasso.load(IMG_BASEURL + item.backdropPath).into( findViewById<ImageView>(R.id.ivDetailImage) )
        }
    }

    fun onClick(view: View) {

        //Send the Person to the URL
        var url = Uri.parse("https://www.themoviedb.org/movie/" + id)
        val intent = Intent(Intent.ACTION_VIEW, url)
        startActivity(intent)
    }
}
