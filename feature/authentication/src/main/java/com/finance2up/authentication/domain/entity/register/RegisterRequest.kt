package com.finance2up.authentication.domain.entity.register

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("username")
    val username: String? = "",
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("password")
    val password: String? = "",
    @SerializedName("full_name")
    val fullName: String? = "",
    @SerializedName("confirm_password")
    val confirmPassword: String? = "",
) {
}