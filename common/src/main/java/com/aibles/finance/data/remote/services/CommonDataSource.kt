package com.aibles.finance.data.remote.services

import com.aibles.finance.data.remote.dto.RefreshTokenRequest
import javax.inject.Inject

class CommonDataSource @Inject constructor(private val service: CommonService) {

    suspend fun refreshToken(refreshTokenRequest: RefreshTokenRequest) = service.refreshToken(refreshTokenRequest)
}