package com.benjweber.httpjsonparser

import android.content.Context
import android.util.Log
import com.benjweber.httpjsonparser.model.Song

class LibraryManager(private val context: Context) {
    private lateinit var library: List<Song>
    lateinit var currentSongInfo: Pair<Int, Song>
    private val apiManager = ApiManager(context)
    var onLibraryReadyListener: OnLibraryReadyListener? = null

    init {
        currentSongInfo = Pair(0, Song("", "", 1, "", ""))
        apiManager.fetchAllSongs {
            this.library = it
            onLibraryReadyListener?.onLibraryReady(it)
            currentSongInfo = Pair(0, it[0])
        }
    }

    fun setCurrentSong(song: Song) {
        for (i in 0 until library.size) {
            if (library[i].equals(song)) {
                currentSongInfo = Pair(i, song)
            }
        }
    }

    fun shuffle(): List<Song> {
        this.library = this.library.toMutableList().apply {
            shuffle()
        }.toList()
        return this.library
    }

    fun nextSong(): Song {
        val nextIndex = currentSongInfo.first+1.rem(library.size)
        this.currentSongInfo = Pair(nextIndex, library[nextIndex])
        return this.currentSongInfo.second
    }

    fun prevSong(): Song {
        val nextIndex = currentSongInfo.first-1.rem(library.size)
        this.currentSongInfo = Pair(nextIndex, library[nextIndex])
        return this.currentSongInfo.second
    }
}