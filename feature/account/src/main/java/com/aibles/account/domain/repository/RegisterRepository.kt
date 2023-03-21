package com.aibles.account.domain.repository

import com.aibles.account.domain.entity.RegisterRequest
import com.aibles.account.domain.entity.RegisterInfo
import com.aibles.finance.data.remote.util.Resource

interface RegisterRepository {
    suspend fun register(registerRequest: RegisterRequest): Resource<RegisterInfo>
}