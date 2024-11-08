package me.szydelko.DTO

import kotlinx.serialization.Serializable
import me.szydelko.util.BitMaskSerializer
import me.szydelko.util.fromBitmaskEnum
import me.szydelko.util.toBitmaskEnum

enum class ErrorFlag(val bit: Long) {
    UNEXPECTED(1L shl 0),
    EXPECTED(1L shl 1);

    companion object : BitMaskSerializer<Set<ErrorFlag>>() {
        override fun toBitmask(flags: Set<ErrorFlag>): Long = toBitmaskEnum(flags, ErrorFlag::bit);
        override fun fromBitmask(mask: Long): Set<ErrorFlag> = fromBitmaskEnum(mask, entries, ErrorFlag::bit)
    }
}

@Serializable
data class DTOError(
    @Serializable(with = ErrorFlag.Companion::class)
    val flag: Set<ErrorFlag>,
)