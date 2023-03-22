package com.finance2up.authentication.presentation.ui.otp

import androidx.lifecycle.ViewModel
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
    private val _emailError = MutableStateFlow("")
    val emailError: StateFlow<String> get() = _emailError

    private val _emailInput = MutableStateFlow("")
    val emailInput: StateFlow<String> get() = _emailInput

    private val _otpNotFull = MutableStateFlow("")
    val otpNotFull: StateFlow<String> get() = _otpNotFull


    fun validateEmail(): Boolean {
        var isValid = true
        if (!emailInput.value.isValidEmail()) {
            _emailError.value = resourcesProvider.getString(R.string.otp_error_entermail)
            isValid = false
        }
        return isValid
    }

    fun changeEmailValue(text: String) {
        _emailInput.value = text
    }

    fun sendEmail() {
    }

    fun checkTextFieldEmpty(
        firstText: String, secondText: String, thirdText: String, fourthText: String
    ) {
        if (firstText == "" || secondText == "" || thirdText == "" || fourthText == "") {
            _otpNotFull.value = resourcesProvider.getString(R.string.otp_error_fill)
        }
    }


}



