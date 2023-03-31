package com.finance2up.authentication.domain.repository

import com.aibles.finance.data.remote.util.Resource
import com.finance2up.authentication.domain.entity.login.LoginResponseEntity
import com.finance2up.authentication.domain.entity.otp.OTPInfo
import com.finance2up.authentication.domain.entity.otp.OTPRequest
import com.finance2up.authentication.domain.entity.otp.PreOTPInfo
import com.finance2up.authentication.domain.entity.otp.PreOTPRequest
import com.finance2up.authentication.domain.entity.register.RegisterInfo
import com.finance2up.authentication.domain.entity.register.RegisterRequest

interface AuthenticationRepository {
    suspend fun login(username: String, password: String): Resource<LoginResponseEntity>

    suspend fun register(registerRequest: RegisterRequest): Resource<RegisterInfo>

    suspend fun sendEmail(preOTPRequest: PreOTPRequest): Resource<PreOTPInfo>

    suspend fun sendOTP(oTPRequest: OTPRequest): Resource<OTPInfo>

}