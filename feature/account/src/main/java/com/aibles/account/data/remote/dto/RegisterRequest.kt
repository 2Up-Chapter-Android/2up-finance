package com.aibles.account.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("username")
    val username: String? = "",
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("password")
    val password: String? = "",
    @SerializedName("fullname")
    val fullname: String? = "",
    @SerializedName("confirm_password")
    val confirm_password: String? = "",
) {
}