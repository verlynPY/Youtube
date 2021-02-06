package com.example.youtube

import ShowSearchList
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.example.youtube.model.Search.SearchStore
import com.example.youtube.model.Utils
import com.example.youtube.model.Utils.SendQuery
import kotlinx.coroutines.launch


class SearchActivity : AppCompatActivity() {

    var TAG = "com.example.youtube"
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(topBar = {
                var light: Color
                if (isSystemInDarkTheme()) {
                    light = Color(30, 30, 30)
                } else {
                    light = Color.White
                }
                TopAppBar(backgroundColor = light) {
                    Row(modifier = Modifier.fillMaxSize()) {
                        IconButton(onClick = { finish() }) {
                            Icon(
                                vectorResource(id = R.drawable.previous), tint = Color(247, 0, 0),
                                modifier = Modifier.absolutePadding(top = 8.dp)
                            )
                        }
                        var query = remember { mutableStateOf("") }
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            trailingIcon = {
                                IconButton(onClick = { query.value = "" }) {
                                    Icon(
                                        vectorResource(id = R.drawable.ic_delete),
                                        tint = Color(247, 0, 0)
                                    )
                                }

                            },
                            value = query.value,
                            onValueChange = { query.value = it },
                            label = {
                                Text(
                                    "Search",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            },
                            activeColor = Color.Red,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                            onImeActionPerformed = { action, softKeyboardController ->
                                if (action == ImeAction.Search) {
                                    lifecycleScope.launch {
                                        //  SetDataStore()
                                        val searchStore = SearchStore(this@SearchActivity)
                                        searchStore.InsertSearch(this@SearchActivity, query.value)
                                        overridePendingTransition(0, 0)
                                        SendQuery(query.value, applicationContext)
                                        overridePendingTransition(0, 0)
                                    }
                                    softKeyboardController?.hideSoftwareKeyboard()
                                }
                            }
                        )
                    }

                }
            }, bodyContent = {

                var searchStore = SearchStore(this@SearchActivity)
                var result = searchStore.ReadSearch()
                LazyColumn {
                    itemsIndexed(items = result) { index, result ->
                        ShowSearchList(search = result, this@SearchActivity)
                    }
                }
            })



            }

    }
}
