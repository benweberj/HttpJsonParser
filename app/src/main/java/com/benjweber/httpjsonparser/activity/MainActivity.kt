package com.benjweber.httpjsonparser.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.benjweber.httpjsonparser.MusicApp
import com.benjweber.httpjsonparser.OnShuffleListener
import com.benjweber.httpjsonparser.OnSongClickedListener
import com.benjweber.httpjsonparser.R
import com.benjweber.httpjsonparser.fragment.LibraryFragment
import com.benjweber.httpjsonparser.fragment.SongInfoFragment
import com.benjweber.httpjsonparser.model.Song
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnSongClickedListener {
    var onShuffleListener: OnShuffleListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val app = application as MusicApp
        val libFrag = LibraryFragment()
        onShuffleListener = libFrag

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragContainer, libFrag, "libfrag")
                .commit()

        ibShuffle.setOnClickListener {
            onShuffleListener?.onShuffle()
        }

        miniPlayer.setOnClickListener {
            var songInfoFrag = getSongInfoFrag()
            if (songInfoFrag == null) {
                songInfoFrag = SongInfoFragment()
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragContainer, songInfoFrag, "songinfofrag")
                    .addToBackStack("")
                    .commit()
            }
        }
        supportFragmentManager.addOnBackStackChangedListener {
            setNav()
        }
        getLibraryFrag()?.libAdapter?.onSongClickedListener = this
    }

    override fun onSongClicked(song: Song) {
        getSongInfoFrag()?.updateSong(song)
        val libManager = (application as MusicApp).libManager
        libManager.setCurrentSong(song)
        tvCurrentSong.text = "${song.title} - ${song.artist}"
    }

    private fun getSongInfoFrag() = supportFragmentManager.findFragmentByTag("songinfofrag") as SongInfoFragment?
    private fun getLibraryFrag() = supportFragmentManager.findFragmentByTag("libfrag") as LibraryFragment?

    private fun setNav() {
        val hasBS = supportFragmentManager.backStackEntryCount > 0
        supportActionBar?.setDisplayHomeAsUpEnabled(hasBS)
        miniPlayer.visibility = if (hasBS) View.INVISIBLE else View.VISIBLE
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return super.onSupportNavigateUp()
    }
}