package com.finance2up.authentication.data.remote.service

import com.finance2up.authentication.data.remote.dto.login.LoginRequest
import com.finance2up.authentication.domain.entity.otp.OTPRequest
import com.finance2up.authentication.domain.entity.otp.EmailRequest
import com.finance2up.authentication.domain.entity.register.RegisterRequest
import javax.inject.Inject

class AuthenticationDataSource @Inject constructor(private val service: AuthenticationService) {
    suspend fun login(loginRequest: LoginRequest) = service.login(loginRequest)
    suspend fun register(registerRequest: RegisterRequest) = service.register(registerRequest)
    suspend fun sendEmail(preOTPRequest: EmailRequest) = service.sendEmail(preOTPRequest)
    suspend fun sendOTP(otpRequest: OTPRequest) = service.sendOTP(otpRequest)
}