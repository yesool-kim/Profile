package com.example.profile.Api.Response

import com.example.profile.Api.Response.Watch

data class WatchSell(
    val _id: String,
    val createdAt: String,
    val currentAmount: Int,
    val detailWallpaperImageUrl: Any,
    val listWallpaperImageUrl: Any,
    val originalAmount: Int,
    val purchased: Boolean,
    val smallListWallpaperImageUrl: Any,
    val title: String,
    val titleAlternative: String,
    val wallpaperImageUrl: Any,
    val watch: Watch,
    val watchSellId: String
)