package me.szydelko.utils

inline infix fun <T> T?.notNull(block: (T) -> Unit): T? {
    if (this != null) block(this)
    return this
}

inline infix fun <T> T?.isNull(block: () -> Unit): T? {
    if (this == null) block()
    return this
}

inline fun <T> T?.nullBlock(notNull: (T) -> Unit,isNull: () -> Unit): T? {
    if (this != null) notNull(this) else isNull()
    return this
}