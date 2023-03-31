package com.finance2up.authentication.domain.usecase

import com.finance2up.authentication.domain.entity.otp.PreOTPRequest
import com.finance2up.authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class PreOTPUseCase @Inject constructor(private val repository: AuthenticationRepository) {
    suspend operator fun invoke(preOTPRequest: PreOTPRequest) = repository.sendEmail(preOTPRequest)
}