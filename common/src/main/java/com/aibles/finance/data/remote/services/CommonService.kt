package com.aibles.finance.data.remote.services

import com.aibles.finance.data.remote.dto.RefreshTokenRequest
import com.aibles.finance.data.remote.util.Resource
import retrofit2.http.Body
import retrofit2.http.POST

interface CommonService {

    @POST("auth/refresh")
    suspend fun refreshToken(@Body refreshTokenRequest: RefreshTokenRequest): Resource<String>
}