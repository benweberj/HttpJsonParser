package com.benjweber.httpjsonparser

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.benjweber.httpjsonparser.model.Song
import com.bumptech.glide.Glide
//libAdapter.onSongClicked = { song -> onSongClickedListener.onSongClicked(song) }
class LibraryAdapter(private var library: List<Song>, private val context: Context, private val act: Context): RecyclerView.Adapter<LibraryAdapter.SongViewHolder>() {
    var onSongClickedListener: OnSongClickedListener? = act as OnSongClickedListener // I am sorry for doing this
//    var onSongClicked: ((song: Song) -> Unit)? = null
//    var onSongLongClicked: ((pos: Int) -> Unit)? = null
//    var onShuffleListener: OnShuffleListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
//        onSongClickedListener = context as OnSongClickedListener
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        return SongViewHolder(view)
    }

    override fun getItemCount() = library.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = library[position]
        holder.attach(song, position)
    }

    fun shuffle(newLib: List<Song>) {
        val callback = SongDiffCallback(newLib, library)
        val diff = DiffUtil.calculateDiff(callback)
        diff.dispatchUpdatesTo(this)
        this.library = newLib
    }

    inner class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvSongName by lazy { itemView.findViewById<TextView>(R.id.tvSongName) }
        private val tvArtistName by lazy { itemView.findViewById<TextView>(R.id.tvArtistName) }
        private val imgThumbnail by lazy { itemView.findViewById<ImageView>(R.id.imgThumbnail) }

        fun attach(song: Song, position: Int) {
            tvSongName.text = song.title
            tvArtistName.text = song.artist
            Glide.with(context).load(song.smallImageURL).into(imgThumbnail)

            itemView.setOnClickListener {
                onSongClickedListener?.onSongClicked(song)
            }
//            itemView.setOnLongClickListener {
//                library = library.toMutableList().apply {
//                    removeAt(position)
//                }.toList()
//                notifyItemRemoved(position)
//                notifyItemRangeChanged(position, library.size)
//                onSongLongClicked?.invoke(position)
//                return@setOnLongClickListener true
//            }
        }
    }
}