package com.benjweber.httpjsonparser.model

import kotlinx.android.parcel.Parcelize

data class Song (
  val title: String,
  val artist: String,
  val durationMillis: Int,
  val smallImageURL: String,
  val largeImageURL: String
)