package com.finance2up.authentication.presentation.ui.otp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OTPViewModel : ViewModel() {
    private var _emailError = MutableLiveData<Boolean>()
    private var _otpNotFull = MutableLiveData<String>()

    val emailError: LiveData<Boolean> get() = _emailError
    val otpNotFull: LiveData<String> get() = _otpNotFull

    fun displayEmailError(email: String) {
        if (email != "" && !isValidEmail(email)) {
            _emailError.value = false
        }
    }

    fun checkTextFieldEmpty(
        firstText: String, secondText: String, thirdText: String, fourthText: String
    ) {
        if (firstText == "" || secondText == "" || thirdText == "" || fourthText == "") {
            _otpNotFull.value = "Please fill 4 number"
        }
    }

    private fun isValidEmail(email: String): Boolean {
        if (email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return true
        return false
    }
}
