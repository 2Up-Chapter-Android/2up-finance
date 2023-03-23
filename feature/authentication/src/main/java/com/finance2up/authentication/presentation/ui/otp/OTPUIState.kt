package com.finance2up.authentication.presentation.ui.otp

data class OTPUIState(
    val textFieldError: String = "",
) {
    val visibilityError get() = textFieldError.isNotEmpty()
}
