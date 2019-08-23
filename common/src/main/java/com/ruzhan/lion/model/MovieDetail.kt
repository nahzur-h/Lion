package com.ruzhan.lion.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieDetail(

        @SerializedName("id")
        var id: String,
        @SerializedName("movieId")
        var movieId: String,
        @SerializedName("title")
        var title: String,
        @SerializedName("tag")
        var tag: String,
        @SerializedName("desc")
        var desc: String,
        @SerializedName("image")
        var image: String,

        @SerializedName("introduces")
        var introduces: List<Introduce>,
        @SerializedName("videos")
        var videos: List<Video>
) : Serializable {

    companion object {

        @JvmStatic
        fun empty(): MovieDetail = MovieDetail(
                "", "", "",
                "", "", "",
                ArrayList(), ArrayList())
    }
}

