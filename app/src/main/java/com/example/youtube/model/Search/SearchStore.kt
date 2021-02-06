package com.example.youtube.model.Search

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import com.bumptech.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy.LOG
import com.google.gson.JsonArray
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.json.JSONArray
import org.json.JSONObject


class SearchStore(context: Context) {

    var dataBaseHelper: DataBaseHelper

    init{
        dataBaseHelper = DataBaseHelper(context = context)
    }

    fun InsertSearch(context: Context,Title:String){
        var result: Boolean = dataBaseHelper.InsertData(Title)

    }

    fun ReadSearch(): ArrayList<Search>{
        var listsearch: ArrayList<Search> = ArrayList()
        var data = dataBaseHelper.ReadData()
        if(data != null && data.count > 0){
            while(data.moveToNext()){
                listsearch.add(Search(data.getString(0),data.getString(1)))
            }
        }

        return listsearch
    }

    fun DeleteSearch(Id: String){
        var delete: Boolean = dataBaseHelper.DeleteData(Id)
    }

}