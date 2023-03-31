package com.finance2up.authentication.domain.entity.otp

import com.google.gson.annotations.SerializedName

data class OTPRequest(
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("otp")
    val otp: String? = "",
)
