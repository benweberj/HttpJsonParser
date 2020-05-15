package com.benjweber.httpjsonparser

import com.benjweber.httpjsonparser.model.Song

interface OnSongClickedListener {
    fun onSongClicked(song: Song)
}