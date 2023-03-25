package com.finance2up.authentication.presentation.ui.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aibles.finance.data.remote.util.Resource
import com.aibles.finance.presentation.utils.ResourcesProvider
import com.aibles.finance.utils.*
import com.finance2up.authentication.R
import com.finance2up.authentication.domain.entity.register.RegisterInfo
import com.finance2up.authentication.domain.entity.register.RegisterRequest
import com.finance2up.authentication.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private var registerUseCase: RegisterUseCase,
    private val resourcesProvider: ResourcesProvider,
) :
    ViewModel() {

    private val _usernameInput = MutableStateFlow("")
    val usernameInput: StateFlow<String> get() = _usernameInput

    private val _fullNameInput = MutableStateFlow("")
    val fullNameInput: StateFlow<String> get() = _fullNameInput

    private val _emailAddressInput = MutableStateFlow("")
    val emailAddressInput: StateFlow<String> get() = _emailAddressInput

    private val _passwordInput = MutableStateFlow("")
    val passwordInput: StateFlow<String> get() = _passwordInput

    private val _passwordConfirmInput = MutableStateFlow("")
    val passwordConfirmInput: StateFlow<String> get() = _passwordConfirmInput

    private val _registerState = MutableStateFlow<Resource<RegisterInfo>>(Resource.loading())
    val registerState: StateFlow<Resource<RegisterInfo>> get() = _registerState

    private val _registerUiState = MutableStateFlow(RegisterUiState())
    val registerUiState: StateFlow<RegisterUiState> get() = _registerUiState

    fun register() {

        if (!isValidateInput()) return

        _registerUiState.value =
            registerUiState.value.copy(
                isLoading = true,
                usernameInput = usernameInput.value,
                fullNameInput = fullNameInput.value,
                emailAddressInput = emailAddressInput.value,
                passwordInput = passwordInput.value,
                passwordConfirmInput = passwordConfirmInput.value
            )

        viewModelScope.launch(Dispatchers.Main) {

            delay(200)

            val registerResponse = registerUseCase(
                RegisterRequest(
                    usernameInput.value,
                    fullNameInput.value,
                    emailAddressInput.value,
                    passwordInput.value,
                    passwordConfirmInput.value
                )
            )

            _registerUiState.value =
                registerUiState.value.copy(isLoading = registerResponse.isLoading())
            _registerState.tryEmit(registerResponse)

            Log.d("check_response", "--- $registerResponse")
        }
    }

    private fun isValidateInput(): Boolean {
        _registerUiState.value = registerUiState.value.copy(
            usernameError = "",
            fullNameError = "",
            passwordError = "",
            emailAddressError = "",
            passwordConfirmError = "",
        )
        var isValid = true
        if (!usernameInput.value.isValidUsername()) {
            _registerUiState.value = registerUiState.value.copy(
                usernameError = resourcesProvider.getString(
                    R.string.register_incorrect_userName
                )
            )
            isValid = false
        }

        if (!fullNameInput.value.isValidFullName()) {
            _registerUiState.value =
                registerUiState.value.copy(
                    fullNameError = resourcesProvider.getString(R.string.register_incorrect_full_name)
                )
            isValid = false
        }

        if (!emailAddressInput.value.isValidEmail()) {
            _registerUiState.value = registerUiState.value.copy(
                emailAddressError = resourcesProvider.getString(
                    R.string.register_incorrect_email
                )
            )
            isValid = false
        }

        if (!passwordInput.value.isValidPassword()) {
            _registerUiState.value = registerUiState.value.copy(
                passwordError = resourcesProvider.getString(
                    R.string.register_incorrect_password
                )
            )
            isValid = false
        }

        if (!passwordConfirmInput.value.isValidPassword()) {
            _registerUiState.value = registerUiState.value.copy(
                passwordConfirmError = resourcesProvider.getString(
                    R.string.register_incorrect_confirm_password
                )
            )
            isValid = false
        }
        return isValid
    }

    fun onUsernameValueChange(text: String) {
        _usernameInput.value = text
        _registerUiState.value = registerUiState.value.copy(isNullUsername = text.isEmpty())
    }

    fun onFullNameValueChange(text: String) {
        _fullNameInput.value = text
        _registerUiState.value = registerUiState.value.copy(isNullFullName = text.isEmpty())
    }

    fun onEmailAddressValueChange(text: String) {
        _emailAddressInput.value = text
        _registerUiState.value = registerUiState.value.copy(isNullEmailAddress = text.isEmpty())
    }

    fun onPasswordValueChange(text: String) {
        _passwordInput.value = text
        _registerUiState.value = registerUiState.value.copy(isNullPassword = text.isEmpty())
    }

    fun onPasswordConfirmValueChange(text: String) {
        _passwordConfirmInput.value = text
        _registerUiState.value = registerUiState.value.copy(isNullPasswordConfirm = text.isEmpty())
    }

}