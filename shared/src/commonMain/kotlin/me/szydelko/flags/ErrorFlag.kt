package me.szydelko.flags

import me.szydelko.utils.BitMaskSerializer
import me.szydelko.utils.bitN
import me.szydelko.utils.fromBitmaskEnum
import me.szydelko.utils.toBitmaskEnum

enum class ErrorFlag(val bit: Long) {
    UNEXPECTED(bitN(0)),
    EXPECTED(bitN(1));

    companion object : BitMaskSerializer<Set<ErrorFlag>>() {
        override fun toBitmask(flags: Set<ErrorFlag>): Long = toBitmaskEnum(flags, ErrorFlag::bit);
        override fun fromBitmask(mask: Long): Set<ErrorFlag> = fromBitmaskEnum(mask, entries, ErrorFlag::bit)
    }
}