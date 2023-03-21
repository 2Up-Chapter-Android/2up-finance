package com.finance2up.authentication.domain.entity.login

import com.aibles.finance.data.local.HawkDataSource


data class LoginResponseEntity(
    val data: LoginResponseData? = null,
    val message: String? = "",
    val status: Int? = 0
){
    data class LoginResponseData(
        val accessToken: String? = HawkDataSource.HawkConst.DEFAULT_VALUE,
        val accessTokenLifeTime: Int? = 0,
        val refreshToken: String? = HawkDataSource.HawkConst.DEFAULT_VALUE,
        val refreshTokenLifeTime: Long? = 0,
        val tokenType: String? = ""
    )
}
