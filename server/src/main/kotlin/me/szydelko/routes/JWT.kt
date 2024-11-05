package me.szydelko.routes

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.szydelko.DTO.DTOLogin
import me.szydelko.DTO.DTORegister
import me.szydelko.models.UserService
import me.szydelko.modules.JwtConfig
import org.koin.ktor.ext.inject
import java.util.*

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
        if ( userService.verifyPassword(user.email, user.password) ){
            call.respond(HttpStatusCode.Unauthorized, "email or password is invalid")
            return@post
        }

        val token = JWT.create()
            .withAudience(jwtConfig.audience)
            .withIssuer(jwtConfig.domain)
            .withClaim("email", user.email)
            .withExpiresAt(Date(System.currentTimeMillis() + 7200000))
            .sign(Algorithm.HMAC256(jwtConfig.secret))
        call.respond(hashMapOf("token" to token))
    }

    authenticate {
        get("/token") {
            val principal = call.principal<JWTPrincipal>()
            val email = principal!!.payload.getClaim("email").asString()
            val expiresAt = principal.expiresAt?.time?.minus(System.currentTimeMillis())
            call.respondText("Hello, $email! Token is expired at $expiresAt ms.")
        }
    }
}