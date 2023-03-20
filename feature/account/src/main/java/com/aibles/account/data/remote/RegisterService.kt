package com.aibles.account.data.remote

import com.aibles.account.domain.entity.RegisterRequest
import com.aibles.account.data.remote.dto.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST
import com.aibles.finance.data.remote.util.Resource


interface RegisterService {
    @POST("/api/v1/auth/users/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Resource<RegisterResponse>
}