package com.benjweber.httpjsonparser.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.benjweber.httpjsonparser.*
import com.benjweber.httpjsonparser.model.Song
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_library.*
import kotlinx.android.synthetic.main.fragment_song_info.*

class SongInfoFragment: Fragment() {
    lateinit var libManager: LibraryManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        libManager = (context.applicationContext as MusicApp).libManager
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_song_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnPrev.setOnClickListener {
            updateSong(libManager.prevSong())
        }
        btnNext.setOnClickListener {
            updateSong(libManager.nextSong())
        }
        btnPlay.setOnClickListener {

        }
        updateSong(libManager.currentSongInfo.second)

//        Log.i("bjw", "${libManager.getLibrary()}")
//        val app = context?.applicationContext as MusicApp
//        app.apiManager.fetchAllSongs {
//            app.libManager.setLibrary(it)
//            val libAdapter = LibraryAdapter(it, app)
//            rvSongs.adapter = libAdapter
//        }


//        libAdapter.onSongClicked = { song -> onSongClickedListener?.onSongClicked(song) }

        // Remove the song from this class's library so shuffling doesn't add them back
//        songListAdapter.onSongLongClicked = { pos ->
//            val title = library[pos].title
//            val farewellMsg = if (title === "Thought Contagion") "Why?" else "$title deleted"
//
//            Toast.makeText(context, farewellMsg, Toast.LENGTH_SHORT).show()
//            library = library.toMutableList().apply {
//                removeAt(pos)
//            }.toList()
//        }

    }

    fun updateSong(song: Song) {
        song.let {
            Glide.with(this).load(song.largeImageURL).into(ivAlbumCover)
            tvSongName.text = song.title
            tvArtist.text = song.artist
        }
    }
}