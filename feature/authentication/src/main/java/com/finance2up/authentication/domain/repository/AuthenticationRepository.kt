package com.finance2up.authentication.domain.repository

import com.aibles.finance.data.remote.util.Resource
import com.finance2up.authentication.domain.entity.login.LoginResponseEntity
import com.finance2up.authentication.domain.entity.register.RegisterInfo
import com.finance2up.authentication.domain.entity.register.RegisterRequest

interface AuthenticationRepository {
    suspend fun login(username: String, password: String): Resource<LoginResponseEntity>

    suspend fun register(registerRequest: RegisterRequest): Resource<RegisterInfo>

}