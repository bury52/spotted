package me.szydelko.routes

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.szydelko.DTO.DTOLogin
import me.szydelko.DTO.DTORegister
import me.szydelko.DTO.DTOUpdate
import me.szydelko.DTO.DTOUser
import me.szydelko.models.UserService
import me.szydelko.modules.JwtConfig
import me.szydelko.utils.nullBlock
import org.koin.ktor.ext.inject
import java.util.*

fun Route.user() {

    val userService: UserService by inject()

    post("/register") {
        val user = call.receive<DTORegister>()
        val message = runCatching { userService.create(user.email, user.password) }
            .getOrElse {
                call.respond(HttpStatusCode.Conflict)
                return@post
            }
        call.respond(
            HttpStatusCode.Created,
            message
        )
    }
    authenticate {
        delete("/account") {
            userService.delete(callId(call.principal()))
            call.respond(HttpStatusCode.OK)
        }
        put("/account") {
            val user = call.receive<DTOUpdate>()
            userService.update(callId(call.principal()), user.email, user.password)
            call.respond(HttpStatusCode.OK)
        }
        get("/account") {
            userService.read(callId(call.principal())).nullBlock({
                call.respond(HttpStatusCode.OK, DTOUser(it[UserService.Users.email]))
            }, {
                call.respond(HttpStatusCode.NoContent)
            })
        }
    }
}

fun Route.jwt() {

    val jwtConfig: JwtConfig by inject()
    val userService: UserService by inject()

    post("/login") {
        val user = call.receive<DTOLogin>()
        val (isVerified, id) = userService.verifyPassword(user.email, user.password)
        if (isVerified) {
            call.respond(HttpStatusCode.Unauthorized, "email or password is invalid")
            return@post
        }

        val token = JWT.create()
            .withAudience(jwtConfig.audience)
            .withIssuer(jwtConfig.domain)
            .withClaim("id", id)
            .withExpiresAt(Date(System.currentTimeMillis() + 7200000))
            .sign(Algorithm.HMAC256(jwtConfig.secret))
        call.respond(hashMapOf("token" to token))
    }

    authenticate {
        get("/token") {
            val principal = call.principal<JWTPrincipal>()
            val expiresAt = principal!!.expiresAt?.time?.minus(System.currentTimeMillis())
            call.respondText("Token is expired at $expiresAt ms.")
        }
    }
}