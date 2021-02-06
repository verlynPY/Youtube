
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageAsset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.ImeOptions
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.youtube.R
import com.example.youtube.model.Search.Search
import com.example.youtube.model.Search.SearchStore
import com.example.youtube.model.Utils.SendQuery
import com.example.youtube.model.Utils.SendVideoUrl
import com.example.youtube.model.video.Item
import com.example.youtubefree.viewmodel.MainViewModel
import kotlinx.coroutines.launch


val imageModifier = Modifier
        .preferredHeight(220.dp)
        .fillMaxWidth()

    @Composable
    fun CardVideo(item: Item, context: Context){
        Card(
            modifier = Modifier
                    .fillMaxWidth()
                    .preferredHeight(280.dp)
                    .clickable(onClick = { item.id!!.videoId?.let { SendVideoUrl(it, context) } }),
            contentColor = Color(138,138,138)
        ){
            var bitmap by remember { mutableStateOf<Bitmap?>(null)}
            Column(modifier = Modifier.absolutePadding(bottom = 2.dp)){

                Glide.with(AmbientContext.current).asBitmap()
                    .load(item.snippet!!.thumbnails!!.medium!!.url)
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            bitmap = resource
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {

                        }

                    })
                if(bitmap != null ){
                Image(bitmap!!.asImageAsset(), modifier = imageModifier
                ,contentScale = ContentScale.Crop)
                }
                Text(text = "${item.snippet!!.title}", fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.absolutePadding(left = 10.dp))
                Text(text = "${item.snippet!!.publishTime}", fontSize = 12.sp,
                    fontWeight = FontWeight.Bold, color = Color.Gray,
                    modifier = Modifier.absolutePadding(left = 10.dp))
            }

        }
    }

        @Composable
        fun CardHome(item: com.example.youtube.model.channel.Item, context: Context){
        Card(
                modifier = Modifier
                        .fillMaxWidth()
                        .preferredHeight(280.dp),
                contentColor = Color(138,138,138)
        ){
            var bitmap by remember { mutableStateOf<Bitmap?>(null)}
            Column(modifier = Modifier.absolutePadding(bottom = 2.dp)){

            Glide.with(AmbientContext.current).asBitmap()
                    .load(item.snippet!!.thumbnails!!.mMedium!!.url)
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onResourceReady(
                                resource: Bitmap,
                                transition: Transition<in Bitmap>?
                        ) {
                            bitmap = resource
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {

                        }

                    })
            if(bitmap != null ){
                Image(bitmap!!.asImageAsset(), modifier = imageModifier
                        ,contentScale = ContentScale.Crop)
            }
            Text(text = "${item.snippet!!.title}", fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.absolutePadding(left = 10.dp))
            Text(text = "${item.snippet!!.title}", fontSize = 12.sp,
                    fontWeight = FontWeight.Bold, color = Color.Gray,
                    modifier = Modifier.absolutePadding(left = 10.dp))
        }

    }
}

    @Composable
    fun ShowSearchList(search: Search, context: Context){
        var searchStore = SearchStore(context = context)
        Box(modifier = Modifier
                .fillMaxWidth()
                .preferredHeight(50.dp)
                .clickable(onClick = {
                    SendQuery(Query = search.Title.toString(), context = context)
                })

        ){
            Row(modifier = Modifier.padding(5.dp), horizontalArrangement = Arrangement.Start){
                Text(search.Title.toString(), fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
            Row(modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.End){
                IconButton(onClick = {
                    searchStore.DeleteSearch(search.Id.toString())
                }) {
                    Icon(vectorResource(id = R.drawable.ic_delete), tint = Color(247,0,0))
                }
            }
        }

    }

    @Composable
    fun Search(context: Context){
        var searchStore: SearchStore = SearchStore(context = context)
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            val password = remember { mutableStateOf(TextFieldValue("")) }
            TextField(
                backgroundColor = Color(230,0,0),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = { Icon(Icons.Filled.Home, tint = Color(255,255,255)) },
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Waffles") },
                activeColor = Color.Red,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                onImeActionPerformed = { action, softKeyboardController ->
                    if (action == ImeAction.Search) {
                        softKeyboardController?.hideSoftwareKeyboard()
                    }
                }
            )
        }
    }

    @Composable
    fun CircularBar(){
        Column(modifier = Modifier.fillMaxSize()){
            Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
                Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()) {

                    CircularProgressIndicator(color = Color(247, 0, 0))

                }
            }

        }
    }
