package me.szydelko.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.enums.EnumEntries
import kotlin.reflect.KProperty1

inline fun <reified T : Enum<T>> toBitmask(flags: Set<T>, bit: KProperty1<T, Int>): Int {
    return flags.fold(0) { acc, flag -> acc or bit.get(flag) }
}

inline fun <reified T : Enum<T>> fromBitmask(mask: Int, entries: EnumEntries<T>, bit: KProperty1<T, Int>): Set<T> {
    return entries.filter { flag -> (mask and bit.get(flag)) != 0 }.toSet()
}

inline fun <reified T : Enum<T>> toBitmask(flags: Set<T>, entries: EnumEntries<T>): Int {
    return flags.fold(0) { acc, flag -> acc or (1 shl entries.indexOf(flag)) }
}

inline fun <reified T : Enum<T>> fromBitmask(mask: Int, entries: EnumEntries<T>): Set<T> {
    return entries.withIndex().filter { (index, flag) -> (mask and (1 shl index)) != 0 }.map { (index, flag) -> flag }
        .toSet()
}

interface BitMask<T> {
    fun toBitmask(flags: T): Int
    fun fromBitmask(mask: Int): T
}

abstract class BitMaskSerializer<T> : KSerializer<T>, BitMask<T> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("BitMask", PrimitiveKind.INT)

    override fun deserialize(decoder: Decoder): T {
        val bitmask = decoder.decodeInt()
        return fromBitmask(bitmask)
    }

    override fun serialize(encoder: Encoder, value: T) {
        val bitmask = toBitmask(value)
        encoder.encodeInt(bitmask)
    }
}
