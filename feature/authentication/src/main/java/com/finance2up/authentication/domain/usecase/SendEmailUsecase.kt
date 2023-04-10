package com.finance2up.authentication.domain.usecase

import com.finance2up.authentication.domain.entity.otp.EmailRequest
import com.finance2up.authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class SendEmailUsecase @Inject constructor(private val repository: AuthenticationRepository) {
    suspend operator fun invoke(preOTPRequest: EmailRequest) = repository.sendEmail(preOTPRequest)
}