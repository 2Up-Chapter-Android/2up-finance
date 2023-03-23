package com.finance2up.authentication.presentation.ui.otp

data class PreOTPUIState(
    val emailError: String = "",
) {
    val visibilityEmailError get() = emailError.isNotEmpty()
}
