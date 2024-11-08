package me.szydelko.utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.enums.EnumEntries
import kotlin.reflect.KProperty1

fun bitN(n: Int): Long = 1L shl n

inline fun <reified T : Enum<T>> toBitmaskEnum(flags: Set<T>, bit: KProperty1<T, Long>): Long {
    return flags.fold(0) { acc, flag -> acc or bit.get(flag) }
}

inline fun <reified T : Enum<T>> fromBitmaskEnum(mask: Long, entries: EnumEntries<T>, bit: KProperty1<T, Long>): Set<T> {
    return entries.filter { flag -> (mask and bit.get(flag)) != 0L }.toSet()
}

inline fun <reified T : Enum<T>> toBitmaskEnum(flags: Set<T>, entries: EnumEntries<T>): Long {
    return flags.fold(0) { acc, flag -> acc or (1L shl entries.indexOf(flag)) }
}

inline fun <reified T : Enum<T>> fromBitmaskEnum(mask: Long, entries: EnumEntries<T>): Set<T> {
    return entries.withIndex().filter { (index, flag) -> (mask and (1L shl index)) != 0L }.map { (index, flag) -> flag }
        .toSet()
}

interface BitMask<T> {
    fun toBitmask(flags: T): Long
    fun fromBitmask(mask: Long): T
}

abstract class BitMaskSerializer<T> : KSerializer<T>, BitMask<T> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("BitMask", PrimitiveKind.LONG)

    override fun deserialize(decoder: Decoder): T {
        val bitmask = decoder.decodeLong()
        return fromBitmask(bitmask)
    }

    override fun serialize(encoder: Encoder, value: T) {
        val bitmask = toBitmask(value)
        encoder.encodeLong(bitmask)
    }
}
