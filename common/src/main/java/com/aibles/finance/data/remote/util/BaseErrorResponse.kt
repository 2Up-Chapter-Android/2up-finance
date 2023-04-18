package com.aibles.finance.data.remote.util

import com.google.gson.annotations.SerializedName

data class BaseErrorResponse(
    @SerializedName("status") val status: Int? = 0,
    @SerializedName("status_message") val statusMessage: String? = "",
    @SerializedName("timestamp") val timestamp: String? = "",
    @SerializedName("data") val data: Data?,
){
    data class Data(
        @SerializedName("code") val code: String? = "",
        @SerializedName("detail") val detail: String? = "",
    )
}
