package com.finance2up.authentication.domain.entity.login


data class LoginResponseEntity(
    val data: LoginResponseData? = null,
    val message: String = "",
    val status: Int = 0
){
    data class LoginResponseData(
        val accessToken: String = "nothing",
        val accessTokenLifeTime: Int = 0,
        val refreshToken: String = "nothing",
        val refreshTokenLifeTime: Long = 0,
        val tokenType: String = ""
    )
}
