package com.hackapet.petsync_kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform