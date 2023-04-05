package com.finance2up.authentication.domain.entity.otp

import com.google.gson.annotations.SerializedName

data class EmailRequest(
    @SerializedName("email")
    val email: String = ""
)
