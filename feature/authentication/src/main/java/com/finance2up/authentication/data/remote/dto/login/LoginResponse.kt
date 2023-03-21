package com.finance2up.authentication.data.remote.dto.login


import com.aibles.finance.data.local.HawkDataSource
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("data")
    val data: LoginResponseData?,
    @SerializedName("message")
    val message: String? = "",
    @SerializedName("status")
    val status: Int? = 0
){
    data class LoginResponseData(
        @SerializedName("access_token")
        val accessToken: String? = HawkDataSource.HawkConst.DEFAULT_VALUE,
        @SerializedName("access_token_life_time")
        val accessTokenLifeTime: Int? = 0,
        @SerializedName("refresh_token")
        val refreshToken: String? = HawkDataSource.HawkConst.DEFAULT_VALUE,
        @SerializedName("refresh_token_life_time")
        val refreshTokenLifeTime: Long? = 0,
        @SerializedName("token_type")
        val tokenType: String? = ""
    )
}