package me.szydelko

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.szydelko.modules.configureKoin
import me.szydelko.modules.configureRoutes

fun main(args: Array<String>) {
    /*embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)*/
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    configureKoin()
    configureRoutes()

    routing {
        get("/") {
            call.respondText("Ktor: ${Greeting().greet()}")
        }
    }
}