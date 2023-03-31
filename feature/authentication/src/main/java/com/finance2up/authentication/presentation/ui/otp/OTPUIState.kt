package com.finance2up.authentication.presentation.ui.otp

data class OTPUIState(
    val isLoading: Boolean = false,
    val textFieldError: String = "",
    val isNullTextField: Boolean = true,
) {
    val visibilityError get() = textFieldError.isNotEmpty()
    val enableSendOTPButton get() = !isLoading && !isNullTextField
}
