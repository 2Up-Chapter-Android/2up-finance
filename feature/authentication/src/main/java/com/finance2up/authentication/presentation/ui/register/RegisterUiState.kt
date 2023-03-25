package com.finance2up.authentication.presentation.ui.register

data class RegisterUiState(

    val isLoading: Boolean = false,
    val usernameInput: String = "",
    val fullNameInput: String = "",
    val emailAddressInput: String = "",
    val passwordInput: String = "",
    val passwordConfirmInput: String = "",

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
    val visibilityUsernameError get() = usernameError.isNotEmpty() || usernameError.isNotBlank()
    val visibilityFullNameError get() = fullNameError.isNotEmpty() || fullNameError.isNotBlank()
    val visibilityEmailAddressError get() = emailAddressError.isNotEmpty() || emailAddressError.isNotBlank()
    val visibilityPasswordError get() = passwordError.isNotEmpty() || passwordError.isNotBlank()
    val visibilityPasswordConfirmError get() = passwordConfirmError.isNotEmpty() || passwordConfirmError.isNotBlank()

    val isNullUsernameInput: Boolean get() = usernameInput.isEmpty()
    val isNullFullNameInput: Boolean get() = fullNameInput.isEmpty()
    val isNullEmailAddressInput: Boolean get() = emailAddressInput.isEmpty()
    val isNullPasswordInput: Boolean get() = passwordInput.isEmpty()
    val isNullPasswordConfirmInput: Boolean get() = passwordConfirmInput.isEmpty()
}
