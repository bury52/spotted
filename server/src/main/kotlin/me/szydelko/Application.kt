package me.szydelko

import io.ktor.server.application.*
import me.szydelko.modules.*

fun main(args: Array<String>) {
    /*embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)*/
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    configureKoin()
    configureHTTP()
    configureSerialization()
    configureSecurity()
    configureRoutes()

}

