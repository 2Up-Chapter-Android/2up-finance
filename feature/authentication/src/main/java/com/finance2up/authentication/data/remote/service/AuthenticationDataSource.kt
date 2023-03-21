package com.finance2up.authentication.data.remote.service

import com.finance2up.authentication.data.remote.dto.login.LoginRequest
import javax.inject.Inject

class AuthenticationDataSource @Inject constructor(private val service: AuthenticationService) {
    suspend fun login(loginRequest: LoginRequest) = service.login(loginRequest)
}