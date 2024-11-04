package me.szydelko.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.szydelko.DTO.DTOLogin
import me.szydelko.DTO.DTORegister
import me.szydelko.models.UserService
import me.szydelko.modules.JwtConfig
import org.koin.ktor.ext.inject

fun Route.jwt() {

    val jwtConfig: JwtConfig by inject()
    val userService: UserService by inject()

    post("/register") {
        val user = call.receive<DTORegister>()
        call.respond(
            HttpStatusCode.Created,
            runCatching { userService.create(user.email, user.password) }
                .getOrElse {
                    call.respond(HttpStatusCode.Conflict)
                }
        )

    }

    post("/login") {
        val user = call.receive<DTOLogin>()

    }

}