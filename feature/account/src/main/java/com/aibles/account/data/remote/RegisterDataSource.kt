package com.aibles.account.data.remote

import com.aibles.account.domain.entity.RegisterRequest
import javax.inject.Inject


class RegisterDataSource @Inject constructor(private val registerService: RegisterService) {
    suspend fun register(registerRequest: RegisterRequest) = registerService.register(registerRequest)
}