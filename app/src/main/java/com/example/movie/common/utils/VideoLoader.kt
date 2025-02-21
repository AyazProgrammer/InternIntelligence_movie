package com.example.movie.common.utils

import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

object VideoLoader {

    fun loadVideo(
        context: Context,
        playerView: PlayerView,
        videoUrl: String,
        autoPlay: Boolean = false
    ): ExoPlayer {
        val exoPlayer = ExoPlayer.Builder(context).build()
        playerView.player = exoPlayer

        val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()

        if (autoPlay) {
            exoPlayer.play()
        }

        return exoPlayer
    }

    fun releasePlayer(exoPlayer: ExoPlayer?) {
        exoPlayer?.release()
    }
}