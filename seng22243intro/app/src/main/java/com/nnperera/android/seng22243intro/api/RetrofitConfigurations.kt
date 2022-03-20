package com.nnperera.android.seng22243intro.api

import com.nnperera.android.seng22243intro.model.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.net.URI.create

interface UserService{
    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("users/{UserId}")
    fun getUser(@Path("userId")id:String): Call<User>
}

class RetrofitConfigurations {

    companion object{
        val API_URL = "https://jsonplaceholder.typicode.com"
        val retrofit= Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val userService= retrofit.create(UserService::class.java)
    }
}