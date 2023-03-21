package com.finance2up.authentication.data.remote.dto.login


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("access_token_life_time")
    val accessTokenLifeTime: Int,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("refresh_token_life_time")
    val refreshTokenLifeTime: Long,
    @SerializedName("token_type")
    val tokenType: String
)