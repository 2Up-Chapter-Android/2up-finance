package com.aibles.account.domain.repository

import com.aibles.account.data.remote.dto.RegisterRequest
import com.aibles.finance.data.remote.util.Resource

interface RegisterRepository {
    suspend fun register(registerRequest: RegisterRequest): Resource<Boolean>
}