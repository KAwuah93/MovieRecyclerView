package com.example.movierecyclerview.model.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.movierecyclerview.R
import com.example.movierecyclerview.model.datasource.remote.retrofit.CONSTANTS.Companion.IMG_BASEURL
import com.example.movierecyclerview.model.moviedataclasses.ResultsItem
import com.example.movierecyclerview.view.activities.DetailView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_1.view.*

class MyAdapter(private val myDataset: ArrayList<ResultsItem>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_1, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val item = myDataset[position]
        holder.bind(item)
    }

    override fun getItemCount() = myDataset.size

    fun addList(additionalData : ArrayList<ResultsItem>) {
        myDataset.addAll(additionalData)
        notifyDataSetChanged()
    }

    //View Holder editing
    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        //Variable of the View
        private var view: View = v
        private var item : ResultsItem? = null

        init { v.setOnClickListener(this) }

        override fun onClick(p0: View?) {
            //On Click function
            val bundle = Bundle()
            bundle.putParcelable("movie", item)
            val intent = Intent(p0?.context,DetailView::class.java)
            intent.putExtras(bundle)
            startActivity(p0!!.context,intent,null)
        }

        //binding function
        fun bind(item : ResultsItem){
            this.item = item
            //binding the view information to the layout
            view.tvListTitle.text = item.title
            view.tvListReleaseDate.text = "Released: "+item.releaseDate
            view.tvListLang.text = "Original Language: "+item.originalLanguage
            view.tvListDesc.text = item.id.toString()
            val picasso = Picasso.get()

            //image loading
            picasso.load(IMG_BASEURL + item.posterPath).into(view.list_item_img)
        }

    }

}