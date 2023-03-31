package com.finance2up.authentication.domain.usecase

import com.finance2up.authentication.domain.entity.otp.OTPRequest
import com.finance2up.authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class OTPUseCase @Inject constructor(private val repository: AuthenticationRepository) {
    suspend operator fun invoke(otpRequest: OTPRequest) = repository.sendOTP(otpRequest)
}