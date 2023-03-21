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

    private val _isLoggingIn = MutableStateFlow(false)
    val isLoggingIn: StateFlow<Boolean> get() = _isLoggingIn

    private val _usernameError = MutableStateFlow("")
    val usernameError: StateFlow<String> get() = _usernameError

    private val _passwordError = MutableStateFlow("")
    val passwordError: StateFlow<String> get() = _passwordError

    fun login() {
        _usernameError.value = ""
        _passwordError.value = ""
        if (!validateInput()) return
        _isLoggingIn.value = true
        viewModelScope.launch {
            delay(200)
            val loginResponse = loginUseCase(usernameInput.value, passwordInput.value)
            _isLoggingIn.tryEmit(loginResponse.isLoading())
            _loginState.tryEmit(loginResponse)

        }
    }

    private fun validateInput(): Boolean {
        var isValid = true
        if (!usernameInput.value.isValidUsername()) {
            _usernameError.value = resourcesProvider.getString(R.string.login_error_notExistAccount)
            isValid = false
        }

        if (!passwordInput.value.isValidPassword()) {
            _passwordError.value = resourcesProvider.getString(R.string.login_error_incorrectPassword)
            isValid = false
        }
        return isValid
    }

    fun onUsernameValueChange(text: String) {
        _usernameInput.value = text
    }

    fun onPasswordValueChange(text: String) {
        _passwordInput.value = text
    }
}