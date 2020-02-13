package com.example.restapiandrecyclerviewandretrofit

import retrofit2.Call
import retrofit2.http.GET

interface ApiService{


    @GET("/realestate")
    fun fetchallusers(): Call<List<User>>


}