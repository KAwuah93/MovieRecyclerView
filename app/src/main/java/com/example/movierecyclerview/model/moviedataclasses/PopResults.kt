package com.example.movierecyclerview.model.moviedataclasses

import com.google.gson.annotations.SerializedName

data class PopResults(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: ArrayList<ResultsItem>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)