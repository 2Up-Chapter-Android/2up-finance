package com.finance2up.authentication.presentation.ui.otp

data class OTPUIState(
    val isLoading: Boolean = false,
    val textFieldError: String = "",
    val firstText: String = "",
    val secondText: String = "",
    val thirdText: String = "",
    val forthText: String = "",
) {
    val enableSubmitButton
        get() = !isLoading && firstText.isNotBlank() && secondText.isNotBlank() && thirdText.isNotBlank() && forthText.isNotBlank()
    val visibilityError get() = textFieldError.isNotEmpty()
}
