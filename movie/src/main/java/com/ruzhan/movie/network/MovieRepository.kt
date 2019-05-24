package com.ruzhan.movie.network

import com.ruzhan.lion.App
import com.ruzhan.lion.db.AppDatabase
import com.ruzhan.lion.db.entity.MovieDetailEntity
import com.ruzhan.lion.db.entity.MovieEntity
import com.ruzhan.lion.model.HttpResult
import com.ruzhan.lion.model.Movie
import com.ruzhan.lion.model.MovieDetail
import io.reactivex.Flowable

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class MovieRepository private constructor() {

    private val api: MovieApi = MovieClient.get()
    private val appDatabase: AppDatabase = AppDatabase[App.get()]

    companion object {

        private var INSTANCE: MovieRepository? = null

        fun get(): MovieRepository {
            if (INSTANCE == null) {
                synchronized(MovieRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = MovieRepository()
                    }
                }
            }
            return this.INSTANCE!!
        }
    }

    fun getMovieList(pageFileName: String): Single<HttpResult<List<Movie>>> {
        return api.getMovieList(pageFileName).subscribeOn(Schedulers.io())
    }

    fun getMovieDetail(detailFile: String): Single<HttpResult<MovieDetail>> {
        return api.getMovieDetail(detailFile).subscribeOn(Schedulers.io())
    }

    fun loadMovieEntityList(): Flowable<List<MovieEntity>> {
        return appDatabase.movieEntityDao().loadMovieEntityList()
    }

    fun insertMovieEntityList(movieEntityList: List<MovieEntity>) {
        return appDatabase.movieEntityDao().insertMovieEntityList(movieEntityList)
    }

    fun loadMovieDetailEntity(movieId: String): Flowable<MovieDetailEntity> {
        return appDatabase.movieDetailEntityDao().loadMovieDetailEntity(movieId)
    }

    fun insertMovieDetailEntity(movieDetailEntity: MovieDetailEntity) {
        return appDatabase.movieDetailEntityDao().insertMovieDetailEntity(movieDetailEntity)
    }
}
