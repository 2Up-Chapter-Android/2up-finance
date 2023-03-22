package com.finance2up.authentication.presentation.ui.otp

data class OTPUIState(
    val emailError: String = "",
) {
    val visibilityEmailError get() = emailError.isNotEmpty()
}
