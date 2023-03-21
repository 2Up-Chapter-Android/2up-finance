package com.finance2up.authentication.data.repository

import com.aibles.finance.AppDispatchers
import com.aibles.finance.data.local.HawkDataSource
import com.aibles.finance.data.remote.util.Resource
import com.finance2up.authentication.data.remote.dto.login.LoginRequest
import com.finance2up.authentication.data.remote.dto.login.LoginResponse
import com.finance2up.authentication.data.remote.service.AuthenticationDataSource
import com.finance2up.authentication.domain.repository.AuthenticationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val dataSource: AuthenticationDataSource,
    private val dispatcher: AppDispatchers,
) : AuthenticationRepository {

    override suspend fun login(username: String, password: String): Resource<LoginResponse> {
        val loginRequest = LoginRequest(password, username)
        var loginResponse: Resource<LoginResponse>
        withContext(dispatcher.io) {
            loginResponse = dataSource.login(loginRequest)
            if (loginResponse.isSuccessful()) {
                HawkDataSource.saveAccessToken(loginResponse.data?.data?.accessToken ?: "null")
                HawkDataSource.saveRefreshToken(loginResponse.data?.data?.refreshToken ?: "null")
            }
        }
        return loginResponse
    }
}