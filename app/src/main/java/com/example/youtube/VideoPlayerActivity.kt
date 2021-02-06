package com.example.youtube

import android.os.Bundle
import android.provider.MediaStore.Video.Thumbnails.VIDEO_ID
import com.example.youtube.model.Credential.Key
import com.google.android.youtube.player.*


class VideoPlayerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    lateinit var youtubePlayerView: YouTubePlayerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player2)
        youtubePlayerView = findViewById(R.id.youtubeplayerview)
        youtubePlayerView.initialize(Key, this)
    }
    override fun onInitializationSuccess(
            p0: YouTubePlayer.Provider?,
            p1: YouTubePlayer?,
            p2: Boolean
    )
    {
        val bundle = intent.extras
        val Url = bundle!!.getCharSequence("Url")
        p1!!.loadVideo(Url.toString())
    }
    override fun onInitializationFailure(
            p0: YouTubePlayer.Provider?,
            p1: YouTubeInitializationResult?
    ) {
    }
}

