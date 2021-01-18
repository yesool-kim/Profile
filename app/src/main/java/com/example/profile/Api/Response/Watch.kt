package com.example.profile.Api.Response

import com.example.profile.Api.Response.Colors
import com.example.profile.Api.Response.Device
import com.example.profile.Api.Response.Images

data class Watch(
    val _id: String,
    val appId: String,
    val colors: Colors,
    val device: Device,
    val images: Images,
    val targetDevice: String
)