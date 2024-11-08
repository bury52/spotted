package me.szydelko.routes

import io.ktor.server.auth.jwt.*

fun callId(jwt : JWTPrincipal?): Long = jwt!!.payload.getClaim("id").asLong()