package com.example.youtube.view

import CardHome
import CardVideo
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.youtube.R
import com.example.youtube.model.Utils
import com.example.youtube.model.video.Item
import com.example.youtubefree.viewmodel.MainViewModel


class HomeActivity2 : AppCompatActivity() {
    val TAG = "com.example.youtubefree"
    lateinit var viewModel: MainViewModel
    private var listVideo:ArrayList<Any> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)


        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        var obj: ArrayList<Item>? = null
        val Lyrics = 'a'..'z'
        var Lyrics_Random = Lyrics.random()
        viewModel.getItem(Lyrics_Random.toString()).observe(this, Observer {
            obj = it as ArrayList<Item>?
            var result = obj
            setContent {
                Scaffold(bottomBar = {
                    BottomAppBar(backgroundColor = Color(247, 0, 0)) {
                        IconButton(modifier = Modifier.preferredWidth(100.dp), onClick = {
                            finish()
                            overridePendingTransition(0, 0)
                            startActivity(intent)
                            overridePendingTransition(0, 0)
                        }) {
                            Icon(
                                    vectorResource(id = R.drawable.ic_youtube_logo),
                                    tint = Color.White
                            )
                        }
                        Spacer(Modifier.weight(1f, true))
                        IconButton(onClick = {
                            overridePendingTransition(0, 0)
                            Utils.GoSearch(applicationContext)
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
    @Composable
    fun CircularBar(){

        Column(modifier = Modifier.fillMaxSize()){
            Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center){

                    CircularProgressIndicator()

            }

        }

    }
}
