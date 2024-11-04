plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.serialization)
    application
}

group = "me.szydelko"
version = "1.0.0"
application {
    mainClass.set("me.szydelko.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.caching.headers)
    implementation(libs.ktor.server.auto.head.response)
    implementation(libs.ktor.server.auth.jwt)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.config.yaml)

    implementation(libs.ktor.serialization.kotlinx.json)

    implementation(libs.koin.ktor)
    implementation(libs.koin.logger.slf4j)

    implementation(libs.exposed.core)
    implementation(libs.exposed.jdbc)
    implementation(libs.h2database)
    implementation(libs.jbcrypt)

    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
}