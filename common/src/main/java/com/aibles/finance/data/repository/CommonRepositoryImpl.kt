package com.aibles.finance.data.repository

import com.aibles.finance.data.local.HawkDataSource
import com.aibles.finance.data.remote.dto.RefreshTokenRequest
import com.aibles.finance.data.remote.services.CommonDataSource
import com.aibles.finance.data.remote.util.Resource
import com.aibles.finance.domain.repository.CommonRepository
import javax.inject.Inject

class CommonRepositoryImpl @Inject constructor(private val dataSource: CommonDataSource):
    CommonRepository {

    override suspend fun refreshToken(): Resource<String> {
        val refreshToken = HawkDataSource.getRefreshToken()
        val refreshTokenRequest = RefreshTokenRequest(refreshToken)
        val refreshTokenResponse = dataSource.refreshToken(refreshTokenRequest)
        if (refreshTokenResponse.isSuccessful()) HawkDataSource.saveAccessToken(refreshTokenResponse.data ?: "null")
        return refreshTokenResponse
    }
}