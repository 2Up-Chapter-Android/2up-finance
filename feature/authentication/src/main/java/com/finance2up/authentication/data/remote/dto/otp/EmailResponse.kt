package com.finance2up.authentication.data.remote.dto.otp

import com.google.gson.annotations.SerializedName

data class EmailResponse(
    @SerializedName("status")
    val status: Int = 0,
    @SerializedName("status_message")
    val statusMessage: String = "",
    @SerializedName("timestamp")
    val timestamp: String = ""
)