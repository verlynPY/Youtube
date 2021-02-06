package com.example.youtube.model.repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.youtube.model.Credential.Key
import com.example.youtube.model.Credential.Url
import com.example.youtube.model.Credential.part
import com.example.youtube.model.channel.Channel
import com.example.youtube.model.video.Item
import com.example.youtube.model.video.Result
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Dat {

    fun getvideos(Query:String): MutableLiveData<List<Item>>{

        val liveData = MutableLiveData<List<Item>>()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Url)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

        GlobalScope.launch(Dispatchers.IO){
        val client: ApiService = retrofit.create<ApiService>(ApiService::class.java)
        client.GetVideo(part,Query,Key).enqueue(object: Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                try{
                    var results: Result? = response.body()
                    var Item = results!!.items
                    var id: List<Item>? = Item
                    liveData!!.value = id
                    Log.e(TAG, "Heyyy ${liveData}")
                }
                catch (e: Exception){
                    Log.e(TAG, e.toString())
                }

            }
            override fun onFailure(call: Call<Result>, t: Throwable) {
                Log.e(TAG, "Heyyy ${t}")
            }
        })
        }
        return liveData!!

    }
    fun getchannel(IdChannel:String): MutableLiveData<List<com.example.youtube.model.channel.Item>>{

        val liveData = MutableLiveData<List<com.example.youtube.model.channel.Item>>()
        val retrofit = Retrofit.Builder()
                .baseUrl(Url)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()

        val client: ApiService = retrofit.create<ApiService>(ApiService::class.java)
        client.GetChannel(part, IdChannel, Key).enqueue(object: Callback<Channel>{
            override fun onResponse(call: Call<Channel>, response: Response<Channel>) {
                val channel = response.body()
                var item = channel!!.items
                var id: List<com.example.youtube.model.channel.Item>? = item
                liveData.value = id
            }

            override fun onFailure(call: Call<Channel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        return liveData


    }

}