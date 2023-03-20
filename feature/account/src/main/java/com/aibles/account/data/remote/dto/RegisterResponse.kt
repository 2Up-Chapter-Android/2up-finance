package com.aibles.account.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("status")
    val status: Int? = 0,
    @SerializedName("status_message")
    val statusMessage: String? = "",
    @SerializedName("timestamp")
    val timestamp: String? = "",
    @SerializedName("data")
    val data: Data?
) {
    data class Data(
        @SerializedName("id")
        val id: String? = "",
        @SerializedName("email")
        val email: String? = "",
        @SerializedName("username")
        val username: String? = "",
        @SerializedName("full_name")
        val fullName: String? = "",
        @SerializedName("activated")
        val activated: String? = "",
    )
}


