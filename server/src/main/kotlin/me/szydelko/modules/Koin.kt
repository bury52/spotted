package me.szydelko.modules

import io.ktor.server.application.*
import me.szydelko.models.UserService
import org.jetbrains.exposed.sql.Database
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(module {

            single {
                JwtConfig(
                    environment.config.property("jwt.domain").getString(),
                    environment.config.property("jwt.audience").getString(),
                    environment.config.property("jwt.realm").getString(),
                    environment.config.property("jwt.secret").getString()
                )
            }

            single {
                Database.connect(
                    url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
                    user = "root",
                    driver = "org.h2.Driver",
                    password = "",
                )
            }

            single {
                UserService(get())
            }

        })
    }

}


data class JwtConfig(val domain: String, val audience: String, val realm: String, val secret: String)