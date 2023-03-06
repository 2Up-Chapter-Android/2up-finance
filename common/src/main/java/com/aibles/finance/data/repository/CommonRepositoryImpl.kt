package com.aibles.finance.data.repository

import com.orhanobut.hawk.Hawk
import com.aibles.finance.constpackage.HawkKey
import com.aibles.finance.data.remote.dto.RefreshTokenRequest
import com.aibles.finance.data.remote.services.CommonDataSource
import com.aibles.finance.data.remote.util.Resource
import com.aibles.finance.domain.repository.CommonRepository
import javax.inject.Inject

class CommonRepositoryImpl @Inject constructor(private val dataSource: CommonDataSource):
    CommonRepository {

    override suspend fun refreshToken(): Resource<String> {
        val refreshToken = Hawk.get(HawkKey.REFRESH_TOKEN, "null")
        val refreshTokenRequest = RefreshTokenRequest(refreshToken)
        val refreshTokenResponse = dataSource.refreshToken(refreshTokenRequest)
        if (refreshTokenResponse.isSuccessful()) Hawk.put(HawkKey.ACCESS_TOKEN, refreshTokenResponse.data)
        return refreshTokenResponse
    }
}