package com.aibles.finance.domain.usecase

import com.aibles.finance.domain.repository.CommonRepository
import javax.inject.Inject

class RefreshTokenUseCase @Inject constructor(private val repository: CommonRepository) {
    suspend operator fun invoke() = repository.refreshToken()
}