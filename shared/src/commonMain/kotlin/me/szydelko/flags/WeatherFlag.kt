package me.szydelko.flags

import me.szydelko.utils.BitMaskSerializer
import me.szydelko.utils.bitN
import me.szydelko.utils.fromBitmaskEnum
import me.szydelko.utils.toBitmaskEnum


/**
 * General Conditions: SUNNY, PARTLY_CLOUDY, CLOUDY, OVERCAST
 *
 * Rain Types: LIGHT_RAIN, RAINY, HEAVY_RAIN
 *
 * Severe Weather: STORMY, THUNDERSTORM, TORNADO, HURRICANE
 *
 * Snow Types: LIGHT_SNOW, SNOWY, HEAVY_SNOW
 *
 * Atmospheric Conditions: FOGGY, MISTY, HAZY, DUST_STORM, SANDSTORM
 *
 * Temperature Extremes: FREEZING, HOT
 *
 * Other Conditions: HUMID, DRY, SLEET, HAIL
 **/
enum class WeatherFlag(val bit: Long) {
    SUNNY(bitN(0)),
    PARTLY_CLOUDY(bitN(1)),
    CLOUDY(bitN(2)),
    OVERCAST(bitN(3)),
    LIGHT_RAIN(bitN(4)),
    RAINY(bitN(5)),
    HEAVY_RAIN(bitN(6)),
    STORMY(bitN(7)),
    THUNDERSTORM(bitN(8)),
    LIGHT_SNOW(bitN(9)),
    SNOWY(bitN(10)),
    HEAVY_SNOW(bitN(11)),
    WINDY(bitN(12)),
    GUSTY(bitN(13)),
    FOGGY(bitN(14)),
    MISTY(bitN(15)),
    HAZY(bitN(16)),
    FREEZING(bitN(17)),
    HOT(bitN(18)),
    HUMID(bitN(19)),
    DRY(bitN(20)),
    SLEET(bitN(21)),
    HAIL(bitN(22)),
    TORNADO(bitN(23)),
    HURRICANE(bitN(24)),
    DUST_STORM(bitN(25)),
    SANDSTORM(bitN(26));

    companion object : BitMaskSerializer<Set<WeatherFlag>>() {
        override fun toBitmask(flags: Set<WeatherFlag>): Long = toBitmaskEnum(flags, WeatherFlag::bit)
        override fun fromBitmask(mask: Long): Set<WeatherFlag> = fromBitmaskEnum(mask, entries, WeatherFlag::bit)
    }
}