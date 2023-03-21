package com.finance2up.authentication.data.repository

import com.aibles.finance.AppDispatchers
import com.aibles.finance.data.local.HawkDataSource
import com.aibles.finance.data.remote.util.Resource
import com.aibles.finance.data.remote.util.map
import com.finance2up.authentication.data.mapping.mapToDomain
import com.finance2up.authentication.data.remote.dto.login.LoginRequest
import com.finance2up.authentication.data.remote.dto.login.LoginResponse
import com.finance2up.authentication.data.remote.service.AuthenticationDataSource
import com.finance2up.authentication.domain.entity.login.LoginResponseEntity
import com.finance2up.authentication.domain.entity.register.RegisterInfo
import com.finance2up.authentication.domain.entity.register.RegisterRequest
import com.finance2up.authentication.domain.repository.AuthenticationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val dataSource: AuthenticationDataSource,
    private val dispatcher: AppDispatchers,
) : AuthenticationRepository {

    override suspend fun login(username: String, password: String): Resource<LoginResponseEntity> {
        val loginRequest = LoginRequest(password, username)
        var loginResponse: Resource<LoginResponseEntity>
        withContext(dispatcher.io) {
            loginResponse = dataSource.login(loginRequest).map { it.mapToDomain() }
            if (loginResponse.isSuccessful()) {
                HawkDataSource.saveAccessToken(loginResponse.data?.data?.accessToken ?: "nothing")
                HawkDataSource.saveRefreshToken(loginResponse.data?.data?.refreshToken ?: "nothing")
            }
        }
        return loginResponse
    }

    override suspend fun register(registerRequest: RegisterRequest): Resource<RegisterInfo> {
        val request = withContext(Dispatchers.IO){
            dataSource.register(registerRequest)
        }

        return request.map {
            it.mapToDomain()
        }
    }

}