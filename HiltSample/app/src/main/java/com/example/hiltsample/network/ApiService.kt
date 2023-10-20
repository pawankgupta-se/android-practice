package com.example.hiltsample.network

import retrofit2.http.GET


/**
 * Dummy api service
 */
interface ApiService {

    @GET("user")
    fun getUserDetail()
}
