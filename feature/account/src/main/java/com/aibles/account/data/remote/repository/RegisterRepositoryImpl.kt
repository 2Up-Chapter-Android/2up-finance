package com.aibles.account.data.remote.repository

import com.aibles.account.data.remote.RegisterDataSource
import com.aibles.account.data.remote.dto.RegisterRequest
import com.aibles.account.domain.repository.RegisterRepository
import com.aibles.finance.data.remote.util.Resource
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(private val registerDataSource: RegisterDataSource): RegisterRepository {
    override suspend fun register(registerRequest: RegisterRequest): Resource<Boolean> {
        return registerDataSource.register(registerRequest)
    }
}