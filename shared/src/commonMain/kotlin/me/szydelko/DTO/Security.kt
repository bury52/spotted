package me.szydelko.DTO

import kotlinx.serialization.Serializable

@Serializable
data class DTORegister(val email: String,val password: String)

@Serializable
data class DTOLogin(val email: String,val password: String)

