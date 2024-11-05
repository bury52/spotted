package me.szydelko.modules

import io.ktor.server.application.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.routing.*
import me.szydelko.routes.jwt

fun Application.configureRoutes() {

    install(AutoHeadResponse)

    routing{
        route("/api"){
            route("/V1"){
                jwt()
            }
        }
    }

}
