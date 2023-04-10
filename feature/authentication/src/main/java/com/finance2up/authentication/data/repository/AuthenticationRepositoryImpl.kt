package com.finance2up.authentication.data.repository

import com.aibles.finance.AppDispatchers
import com.aibles.finance.data.local.HawkDataSource
import com.aibles.finance.data.remote.util.Resource
import com.aibles.finance.data.remote.util.map
import com.finance2up.authentication.data.mapping.mapToDomain
import com.finance2up.authentication.data.remote.dto.login.LoginRequest
import com.finance2up.authentication.data.remote.service.AuthenticationDataSource
import com.finance2up.authentication.domain.entity.login.LoginResponseEntity
import com.finance2up.authentication.domain.entity.otp.OTPInfo
import com.finance2up.authentication.domain.entity.otp.OTPRequest
import com.finance2up.authentication.domain.entity.otp.EmailInfo
import com.finance2up.authentication.domain.entity.otp.EmailRequest
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
                HawkDataSource.saveAccessToken(
                    loginResponse.data?.data?.accessToken ?: HawkDataSource.HawkConst.DEFAULT_VALUE
                )
                HawkDataSource.saveRefreshToken(
                    loginResponse.data?.data?.refreshToken ?: HawkDataSource.HawkConst.DEFAULT_VALUE
                )
            }
        }
        return loginResponse
    }

    override suspend fun register(registerRequest: RegisterRequest): Resource<RegisterInfo> {
        val response = withContext(Dispatchers.IO) {
            dataSource.register(registerRequest)
        }

        return response.map {
            it.mapToDomain()
        }
    }

    override suspend fun sendEmail(preOTPRequest: EmailRequest): Resource<EmailInfo> {
        val response = withContext(Dispatchers.IO) {
            dataSource.sendEmail(preOTPRequest)
        }

        return response.map {
            it.mapToDomain()
        }
    }

    override suspend fun sendOTP(otpRequest: OTPRequest): Resource<OTPInfo> {
        val response = withContext(Dispatchers.IO) {
            dataSource.sendOTP(otpRequest)
        }

        return response.map {
            it.mapToDomain()
        }
    }

}