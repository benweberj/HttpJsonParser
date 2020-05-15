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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_library.*

class LibraryFragment: Fragment(), OnLibraryReadyListener, OnShuffleListener {
    lateinit var libManager: LibraryManager
    lateinit var libAdapter: LibraryAdapter
    lateinit var act: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val app = context.applicationContext as MusicApp
        libManager = app.libManager
        libManager.onLibraryReadyListener = this
        act = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onLibraryReady(library: List<Song>) {
        libAdapter = LibraryAdapter(library, context?.applicationContext as MusicApp, act)
        rvSongs.adapter = libAdapter
    }

    override fun onShuffle() {
        libAdapter.shuffle(libManager.shuffle())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
}