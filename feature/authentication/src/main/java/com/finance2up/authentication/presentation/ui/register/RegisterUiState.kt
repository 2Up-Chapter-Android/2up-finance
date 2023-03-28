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

    val usernameInput: String= "",
    val fullNameInput: String= "",
    val emailAddressInput: String= "",
    val passwordInput: String= "",
    val confirmPasswordInput: String= "",
    ) {
    val enableLoginButton get() = !isLoading && !isNullUsername && !isNullFullName && !isNullEmailAddress && !isNullPassword && !isNullPasswordConfirm
    val visibilityUsernameError get() = usernameError.isNotBlank()
    val visibilityFullNameError get() = fullNameError.isNotBlank()
    val visibilityEmailAddressError get() = emailAddressError.isNotBlank()
    val visibilityPasswordError get() = passwordError.isNotBlank()
    val visibilityConfirmPasswordError get() = passwordConfirmError.isNotBlank()
}