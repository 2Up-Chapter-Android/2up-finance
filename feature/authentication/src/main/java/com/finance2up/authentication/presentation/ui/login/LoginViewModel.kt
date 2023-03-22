package com.finance2up.authentication.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aibles.finance.data.remote.util.Resource
import com.aibles.finance.presentation.utils.ResourcesProvider
import com.aibles.finance.utils.isValidPassword
import com.aibles.finance.utils.isValidUsername
import com.finance2up.authentication.R
import com.finance2up.authentication.domain.entity.login.LoginResponseEntity
import com.finance2up.authentication.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val resourcesProvider: ResourcesProvider,
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _usernameInput = MutableStateFlow("")
    val usernameInput: StateFlow<String> get() = _usernameInput

    private val _passwordInput = MutableStateFlow("")
    val passwordInput: StateFlow<String> get() = _passwordInput

    private val _loginState = MutableStateFlow<Resource<LoginResponseEntity>>(Resource.loading())
    val loginState: StateFlow<Resource<LoginResponseEntity>> get() = _loginState

    private val _loginUiState = MutableStateFlow(LoginUIState())
    val loginUiState: StateFlow<LoginUIState> get() = _loginUiState

    fun login() {
        if (!validateInput()) return
        _loginUiState.value = loginUiState.value.copy(isLoading = true)
        viewModelScope.launch {
            delay(200)
            val loginResponse = loginUseCase(usernameInput.value, passwordInput.value)
//            _loginUiState.tryEmit(loginResponse.isLoading())
            _loginUiState.value = loginUiState.value.copy(isLoading = loginResponse.isLoading())
            _loginState.tryEmit(loginResponse)
        }
    }

    private fun validateInput(): Boolean {
        _loginUiState.value = loginUiState.value.copy(usernameError = "", passwordError = "")
        var isValid = true
        if (!usernameInput.value.isValidUsername()) {
            _loginUiState.value = loginUiState.value.copy(usernameError = resourcesProvider.getString(R.string.login_error_notExistAccount))
            isValid = false
        }

        if (!passwordInput.value.isValidPassword()) {
            _loginUiState.value = loginUiState.value.copy(passwordError = resourcesProvider.getString(R.string.login_error_incorrectPassword))
            isValid = false
        }
        return isValid
    }

    fun onUsernameValueChange(text: String) {
        _usernameInput.value = text
        _loginUiState.value = loginUiState.value.copy(isNullUsername = text.isEmpty())
    }

    fun onPasswordValueChange(text: String) {
        _passwordInput.value = text
        _loginUiState.value = loginUiState.value.copy(isNullPassword = text.isEmpty())
    }
}