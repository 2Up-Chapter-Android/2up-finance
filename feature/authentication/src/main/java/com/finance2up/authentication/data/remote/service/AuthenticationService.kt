package com.finance2up.authentication.data.remote.service

import com.aibles.finance.data.remote.util.Resource
import com.finance2up.authentication.data.remote.dto.login.LoginRequest
import com.finance2up.authentication.data.remote.dto.login.LoginResponse
import com.finance2up.authentication.data.remote.dto.register.RegisterResponse
import com.finance2up.authentication.domain.entity.register.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Resource<LoginResponse>

    @POST("auth/users/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Resource<RegisterResponse>
}