package com.aibles.finance.domain.repository

import com.aibles.finance.data.remote.util.Resource

interface CommonRepository {
    suspend fun refreshToken(): Resource<String>
}