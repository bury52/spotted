package me.szydelko.DTO

import kotlinx.serialization.Serializable
import me.szydelko.flags.ErrorFlag


@Serializable
data class DTOError(
    @Serializable(with = ErrorFlag.Companion::class)
    val flag: Set<ErrorFlag>,
)