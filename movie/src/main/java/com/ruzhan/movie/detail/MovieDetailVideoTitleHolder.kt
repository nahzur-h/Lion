package com.ruzhan.movie.detail

import android.support.v7.widget.RecyclerView
import android.view.View
import com.ruzhan.lion.helper.FontHelper
import kotlinx.android.synthetic.main.item_movie_detail_video_title.view.*

class MovieDetailVideoTitleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.title_tv.typeface = FontHelper.get().getBoldTypeface()
    }
}