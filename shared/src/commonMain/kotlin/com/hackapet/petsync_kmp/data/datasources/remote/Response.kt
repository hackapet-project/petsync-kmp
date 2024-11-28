package com.hackapet.petsync_kmp.data.datasources.remote

import kotlinx.serialization.Serializable

@Serializable
class Response<out T>(val success: Boolean, val data: T)