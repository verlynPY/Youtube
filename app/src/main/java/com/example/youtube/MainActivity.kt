package com.example.youtube

import CardVideo
import CircularBar
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.youtube.model.Credential
import com.example.youtube.model.Utils.GoHome
import com.example.youtube.model.Utils.GoSearch
import com.example.youtube.model.video.Item
import com.example.youtubefree.viewmodel.MainViewModel
import com.google.android.youtube.player.YouTubeStandalonePlayer


@Suppress("DEPRECATION")
    class MainActivity : AppCompatActivity() {

    val TAG = "com.example.youtubefree"
    lateinit var viewModel: MainViewModel
    private var listVideo:ArrayList<Any> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)

        setContent{
            CircularBar()
        }

        val bundle = intent.extras
        var Query = bundle!!.getCharSequence("Query")
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        var obj: ArrayList<Item>? = null
        viewModel.getItem(Query.toString()).observe(this, Observer {

            when (it.size) {
                0 ->
                    Toast.makeText(applicationContext, "Funciona", Toast.LENGTH_SHORT).show()
            }

            obj = it as ArrayList<Item>?
            var result = obj
            setContent {

                Scaffold(bottomBar = {
                    BottomAppBar(backgroundColor = Color(247, 0, 0)) {
                        IconButton(modifier = Modifier.preferredWidth(100.dp), onClick = {
                            GoHome(this@MainActivity)
                        } ) {
                            Icon(
                                    vectorResource(id = R.drawable.ic_youtube_logo),
                                    tint = Color.White
                            )
                        }

                        Spacer(Modifier.weight(1f, true))
                        IconButton(onClick = {
                            overridePendingTransition(0, 0)
                            GoSearch(applicationContext)
                            overridePendingTransition(0, 0)

                        }) {
                            Icon(Icons.Filled.Search, tint = Color.White)
                        }
                    }
                }, bodyContent = {

                    LazyColumn {
                        if (obj != null) {
                            itemsIndexed(items = result!!) { index, result ->
                                CardVideo(item = result, applicationContext)
                            }
                        }
                    }
                })
            }
        })
    }

    }
