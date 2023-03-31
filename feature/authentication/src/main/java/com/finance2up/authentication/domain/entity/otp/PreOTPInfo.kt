package com.finance2up.authentication.domain.entity.otp


data class PreOTPInfo(
    val status: Int? = 0,
    val statusMessage: String? = "",
    val timestamp: String? = "",
    val data: String? = ""
) {
}