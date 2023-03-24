package com.finance2up.authentication.presentation.ui.register

data class RegisterUiState(
    val isLoading: Boolean = false,
    val usernameError: String = "",
    val fullNameError: String = "",
    val emailAddressError: String = "",
    val passwordError: String = "",
    val passwordConfirmError: String = "",
    val isNullUsername: Boolean = true,
    val isNullFullName: Boolean = true,
    val isNullEmailAddress: Boolean = true,
    val isNullPassword: Boolean = true,
    val isNullPasswordConfirm: Boolean = true,
) {
    val enableLoginButton get() = !isLoading && !isNullUsername && !isNullFullName && !isNullEmailAddress && !isNullPassword && !isNullPasswordConfirm
    val visibilityUsernameError get() = usernameError.isNotEmpty()
    val visibilityFullNameError get() = fullNameError.isNotEmpty()
    val visibilityEmailAddressError get() = emailAddressError.isNotEmpty()
    val visibilityPasswordError get() = passwordError.isNotEmpty()
    val visibilityPasswordConfirmError get() = passwordConfirmError.isNotEmpty()
}
