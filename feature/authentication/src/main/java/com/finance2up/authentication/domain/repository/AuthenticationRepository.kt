package com.finance2up.authentication.domain.repository

import com.aibles.finance.data.remote.util.Resource
import com.finance2up.authentication.data.remote.dto.login.LoginResponse

interface AuthenticationRepository {
    suspend fun login(username: String, password: String): Resource<LoginResponse>
}