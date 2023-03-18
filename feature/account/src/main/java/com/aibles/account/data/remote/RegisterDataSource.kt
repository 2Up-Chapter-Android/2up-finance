package com.aibles.account.data.remote

import com.aibles.account.data.remote.dto.RegisterRequest
import javax.inject.Inject


class RegisterDataSource @Inject constructor(private val registerService: RegisterService) {
    suspend fun register(registerRequest: RegisterRequest) = registerService.register(registerRequest)
}