package com.finance2up.authentication.domain.entity.otp

import com.google.gson.annotations.SerializedName

data class PreOTPRequest(
    @SerializedName("email")
    val email: String? = ""
)
