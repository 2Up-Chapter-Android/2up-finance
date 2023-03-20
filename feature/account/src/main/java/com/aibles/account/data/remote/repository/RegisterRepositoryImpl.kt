package com.aibles.account.data.remote.repository

import com.aibles.account.data.mapping.mapToDomain
import com.aibles.account.data.remote.RegisterDataSource
import com.aibles.account.domain.entity.RegisterRequest
import com.aibles.account.domain.entity.RegisterInfo
import com.aibles.account.domain.repository.RegisterRepository
import com.aibles.finance.data.remote.util.Resource
import com.aibles.finance.data.remote.util.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(private val registerDataSource: RegisterDataSource): RegisterRepository {
    override suspend fun register(registerRequest: RegisterRequest): Resource<RegisterInfo> {
        val request = withContext(Dispatchers.IO){
            registerDataSource.register(registerRequest)
        }

        return request.map {
            it?.mapToDomain() ?: RegisterInfo()
        }
    }
}