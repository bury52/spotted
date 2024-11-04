package me.szydelko.models

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

// transaction
suspend fun <T> dbQuery(block: suspend () -> T): T =
    newSuspendedTransaction(Dispatchers.IO) { block() }


// null value
infix fun <T> T?.notNull(block: T.() -> Unit): T? {
    if (this != null) block(this)
    return this
}

infix fun <T> T?.isNull(block: () -> Unit): T? {
    if (this == null) block()
    return this
}

fun <T> T?.nullBlock(notNull: T.() -> Unit,isNull: () -> Unit): T? {
    if (this != null) notNull(this) else isNull()
    return this
}