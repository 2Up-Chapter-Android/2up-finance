package com.finance2up.authentication.data.remote.service

import com.aibles.finance.data.remote.util.Resource
import com.finance2up.authentication.data.remote.dto.login.LoginRequest
import com.finance2up.authentication.data.remote.dto.login.LoginResponse
import com.finance2up.authentication.data.remote.dto.otp.OTPResponse
import com.finance2up.authentication.data.remote.dto.otp.PreOTPResponse
import com.finance2up.authentication.data.remote.dto.register.RegisterResponse
import com.finance2up.authentication.domain.entity.otp.OTPRequest
import com.finance2up.authentication.domain.entity.otp.PreOTPRequest
import com.finance2up.authentication.domain.entity.register.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Resource<LoginResponse>

    @POST("auth/users/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Resource<RegisterResponse>

    @POST("auth/otp/resend")
    suspend fun sendEmail(@Body preOTPRequest: PreOTPRequest): Resource<PreOTPResponse>

    @POST("auth/users/active")
    suspend fun sendOTP(@Body otpRequest: OTPRequest): Resource<OTPResponse>
}