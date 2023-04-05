package com.finance2up.authentication.presentation.ui.otp

data class OTPUIState(
    val isLoading: Boolean = false,
    val firstText: String = "",
    val secondText: String = "",
    val thirdText: String = "",
    val forthText: String = "",
    val email: String = "",
    var emailError: String = "",
    var otpExpiredError: String = "",

    ) {
    val enableActiveButton
        get() = !isLoading && firstText.isNotBlank() && secondText.isNotBlank() && thirdText.isNotBlank() && forthText.isNotBlank() && email.isNotBlank()
    var visibilityOTPExpiredError = false
    val visibilityEmailError get() = emailError.isNotEmpty()

}
