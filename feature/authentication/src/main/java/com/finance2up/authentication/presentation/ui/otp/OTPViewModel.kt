package com.finance2up.authentication.presentation.ui.otp

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.aibles.finance.presentation.utils.ResourcesProvider
import com.finance2up.authentication.R
import com.finance2up.authentication.presentation.util.isValidEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class OTPViewModel @Inject constructor(
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {

    private val _emailInput = MutableStateFlow("")
    val emailInput: StateFlow<String> get() = _emailInput

    private val _preOtpUiState = MutableStateFlow(PreOTPUIState())
    val preOtpUIState: StateFlow<PreOTPUIState> get() = _preOtpUiState

    private val _otpUiState = MutableStateFlow(OTPUIState())
    val oTPUIState: StateFlow<OTPUIState> get() = _otpUiState

    private val _firstText = MutableStateFlow("")
    val firstText: StateFlow<String> get() = _firstText
    private val _secondText = MutableStateFlow("")
    val secondText: StateFlow<String> get() = _secondText
    private val _thirdText = MutableStateFlow("")
    val thirdText: StateFlow<String> get() = _thirdText
    private val _forthText = MutableStateFlow("")
    val forthText: StateFlow<String> get() = _forthText

    private fun validateEmail(): Boolean {
        _preOtpUiState.value = preOtpUIState.value.copy(emailError = "")

        var isValid = true
        if (!emailInput.value.isValidEmail()) {
            _preOtpUiState.value =
                preOtpUIState.value.copy(emailError = resourcesProvider.getString(R.string.otp_error_entermail))
            isValid = false
        }
        return isValid
    }

    fun changeEmailValue(text: String) {
        _emailInput.value = text
    }

    fun sendEmail(navController: NavController) {
        if (!validateEmail()) {
            return
        }
        navController.navigate(route = "OTPScreen/${emailInput.value}")
    }

    fun changeOTPFirstTextValue(text: String) {
        _firstText.value = text
    }

    fun changeOTPSecondTextValue(text: String) {
        _secondText.value = text
    }

    fun changeOTPThirdTextValue(text: String) {
        _thirdText.value = text
    }

    fun changeOTPForthTextValue(text: String) {
        _forthText.value = text
    }

    private fun validateOTP(): Boolean {
        _otpUiState.value = oTPUIState.value.copy(textFieldError = "")
        var isValid = true

        if (_firstText.value.isEmpty() || _secondText.value.isEmpty() || _thirdText.value.isEmpty() || _forthText.value.isEmpty()) {
            _otpUiState.value =
                oTPUIState.value.copy(textFieldError = resourcesProvider.getString(R.string.otp_error_fill))
            isValid = false
        }
        return isValid
    }

    fun sendOTP(navController: NavController) {
        if (!validateOTP()) {
            return
        }
        navController.navigate("LoginScreen")
    }
}



