package com.example.articles

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform