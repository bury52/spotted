package me.szydelko

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform