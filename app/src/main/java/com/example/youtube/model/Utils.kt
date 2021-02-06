package com.example.youtube.model

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.ui.text.input.TextFieldValue
import com.example.youtube.MainActivity
import com.example.youtube.SearchActivity
import com.example.youtube.VideoPlayerActivity
import com.example.youtube.view.HomeActivity

object Utils {

    fun SendVideoUrl(Url: String, context: Context){
        val intent = Intent(context, VideoPlayerActivity::class.java)
        val bundle = Bundle()
        bundle.putString("Url", Url)
        intent.putExtras(bundle)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

        fun SendQuery(Query: String, context: Context){
            val intent = Intent(context, MainActivity::class.java)
            val bundle = Bundle()
            bundle.putString("Query", Query)
            intent.putExtras(bundle)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }


        fun GoSearch(context: Context){
            val intent = Intent(context, SearchActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
        fun GoHome(context: Context){
            val intent = Intent(context, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }



}