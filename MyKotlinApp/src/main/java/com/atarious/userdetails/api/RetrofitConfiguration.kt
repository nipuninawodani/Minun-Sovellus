package com.atarious.userdetails.api

import com.atarious.userdetails.Model.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

    interface UserApiServices {
        @GET("users")
        fun getUsers(): Call<List<User>>

        @GET("users/{ID}")
        fun GetUser(@Path("ID") id: String): Call<User>

        companion object {
            val api_Url = "https://jsonplaceholder.typicode.com/"
            fun create():UserApiServices{
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(api_Url)
                    .build()
                return retrofit.create(UserApiServices::class.java)
            }
        }
    }
