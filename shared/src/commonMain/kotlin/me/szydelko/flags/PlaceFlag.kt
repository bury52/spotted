package me.szydelko.flags

import me.szydelko.messages.PlaceFlagMessage
import me.szydelko.utils.BitMaskSerializer
import me.szydelko.utils.bitN
import me.szydelko.utils.fromBitmaskEnum
import me.szydelko.utils.toBitmaskEnum

enum class PlaceFlag(val bit: Long, val message: PlaceFlagMessage) {
    // Positive Flags
    SEATING_AVAILABLE(bitN(0), PlaceFlagMessage.SEATING_AVAILABLE),
    SHELTER_AVAILABLE(bitN(1), PlaceFlagMessage.SHELTER_AVAILABLE),
    SHADED_AREA(bitN(2), PlaceFlagMessage.SHADED_AREA),
    BATHROOM_AVAILABLE(bitN(3), PlaceFlagMessage.BATHROOM_AVAILABLE),
    WATER_SOURCE(bitN(4), PlaceFlagMessage.WATER_SOURCE),
    PICNIC_AREA(bitN(5), PlaceFlagMessage.PICNIC_AREA),
    CAMPFIRE_ALLOWED(bitN(6), PlaceFlagMessage.CAMPFIRE_ALLOWED),
    PARKING_AVAILABLE(bitN(7), PlaceFlagMessage.PARKING_AVAILABLE),
    PET_FRIENDLY(bitN(8), PlaceFlagMessage.PET_FRIENDLY),
    PLAYGROUND(bitN(9), PlaceFlagMessage.PLAYGROUND),
    TRAIL_ACCESS(bitN(10), PlaceFlagMessage.TRAIL_ACCESS),
    ACCESSIBLE(bitN(11), PlaceFlagMessage.ACCESSIBLE),
    BIKE_RACKS(bitN(12), PlaceFlagMessage.BIKE_RACKS),
    ELECTRICITY_AVAILABLE(bitN(13), PlaceFlagMessage.ELECTRICITY_AVAILABLE),
    BBQ_GRILL(bitN(14), PlaceFlagMessage.BBQ_GRILL),

    // Scents
    FLORAL_SCENT(bitN(15), PlaceFlagMessage.FLORAL_SCENT),
    FRESH_AIR(bitN(16), PlaceFlagMessage.FRESH_AIR),
    PINE_SCENT(bitN(17), PlaceFlagMessage.PINE_SCENT),
    SEA_BREEZE(bitN(18), PlaceFlagMessage.SEA_BREEZE),

    // Negative Flags
    MUDDY(bitN(19), PlaceFlagMessage.MUDDY),
    LITTERED(bitN(20), PlaceFlagMessage.LITTERED),
    SMELLY(bitN(21), PlaceFlagMessage.SMELLY),
    NOISY(bitN(22), PlaceFlagMessage.NOISY),
    FLOODED(bitN(23), PlaceFlagMessage.FLOODED),
    INSECTS_PRESENT(bitN(24), PlaceFlagMessage.INSECTS_PRESENT),
    UNSAFE_AREA(bitN(25), PlaceFlagMessage.UNSAFE_AREA),
    OVERCROWDED(bitN(26), PlaceFlagMessage.OVERCROWDED),
    POOR_LIGHTING(bitN(27), PlaceFlagMessage.POOR_LIGHTING);

    companion object : BitMaskSerializer<Set<PlaceFlag>>() {
        override fun toBitmask(flags: Set<PlaceFlag>): Long = toBitmaskEnum(flags, PlaceFlag::bit)
        override fun fromBitmask(mask: Long): Set<PlaceFlag> = fromBitmaskEnum(mask, entries, PlaceFlag::bit)
    }
}