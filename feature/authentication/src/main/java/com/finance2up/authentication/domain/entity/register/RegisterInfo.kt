package com.finance2up.authentication.domain.entity.register


data class RegisterInfo(
    val status: Int? = 0,
    val statusMessage: String? = "",
    val timestamp: String? = "",
    val data: AccountInformation? = null
) {
    data class AccountInformation(
        val id: String? = "",
        val email: String? = "",
        val username: String? = "",
        val fullName: String? = "",
        val activated: String? = ""
    )
}