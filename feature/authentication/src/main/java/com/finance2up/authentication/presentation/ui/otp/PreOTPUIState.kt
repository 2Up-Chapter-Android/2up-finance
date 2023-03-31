package com.finance2up.authentication.presentation.ui.otp

data class PreOTPUIState(
    val isLoading: Boolean = false,
    val emailError: String = "",
    val email: String = "",
) {
    val enableSendEmailButton
        get() = !isLoading && email.isNotBlank()
    val visibilityEmailError get() = emailError.isNotEmpty()
}
