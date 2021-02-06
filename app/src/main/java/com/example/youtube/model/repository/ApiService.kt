package com.example.youtube.model.repository

import com.example.youtube.model.channel.Channel
import com.example.youtube.model.video.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

   @GET("/youtube/v3/search")
   fun GetVideo(@Query("part") part:String, @Query("q") query:String, @Query("key") key:String): Call<Result>


   @GET("/youtube/v3/channels")
   fun GetChannel(@Query("part") part: String, @Query("id") id:String, @Query("key") key:String): Call<Channel>

}