package com.aibles.finance.data.remote.util

import com.google.gson.annotations.SerializedName

data class BaseErrorResponse(
    @SerializedName("errors")
    val errors: List<Error>
)

data class Error(
    @SerializedName("errorCode")
    val errorCode: String,
    @SerializedName("message")
    val message: String
)
