package com.example.youtubefree.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.youtube.model.Search.SearchStore
import com.example.youtube.model.repository.Dat
import com.example.youtube.model.video.Item
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val repository = Dat()


    fun getItem(Query:String = "Anuel"): MutableLiveData<List<Item>>{
        return repository.getvideos(Query)
    }
    fun getChannel(Id:String): MutableLiveData<List<com.example.youtube.model.channel.Item>> {
        return repository.getchannel(Id)
    }




}