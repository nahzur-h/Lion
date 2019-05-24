package com.ruzhan.lion.network

import okhttp3.OkHttpClient

class CommonHttpClient private constructor() {

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()

    companion object {

        private var httpClient: CommonHttpClient? = null

        fun get(): CommonHttpClient {
            if (httpClient == null) {
                synchronized(CommonHttpClient::class.java) {
                    if (httpClient == null) {
                        httpClient = CommonHttpClient()
                    }
                }
            }
            return this.httpClient!!
        }

        @JvmStatic
        fun getCommonHttpClient(): OkHttpClient = get().okHttpClient
    }
}
