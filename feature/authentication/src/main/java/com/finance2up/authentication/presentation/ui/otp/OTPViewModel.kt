package com.finance2up.authentication.presentation.ui.otp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aibles.finance.data.remote.util.Resource
import com.aibles.finance.presentation.utils.ResourcesProvider
import com.aibles.finance.utils.isValidEmail
import com.finance2up.authentication.R
import com.finance2up.authentication.domain.entity.otp.OTPInfo
import com.finance2up.authentication.domain.entity.otp.OTPRequest
import com.finance2up.authentication.domain.entity.otp.PreOTPInfo
import com.finance2up.authentication.domain.entity.otp.PreOTPRequest
import com.finance2up.authentication.domain.usecase.OTPUseCase
import com.finance2up.authentication.domain.usecase.PreOTPUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OTPViewModel @Inject constructor(
    private val resourcesProvider: ResourcesProvider,
    private val preOTPUseCase: PreOTPUseCase,
    private val otpUseCase: OTPUseCase

) : ViewModel() {

    private val _preOTPState = MutableStateFlow<Resource<PreOTPInfo>>(Resource.loading())
    val preOTPState: StateFlow<Resource<PreOTPInfo>> get() = _preOTPState

    private val _preotpUIState = MutableStateFlow(PreOTPUIState())
    val preotpUIState: StateFlow<PreOTPUIState> get() = _preotpUIState


    private val _otpState = MutableStateFlow<Resource<OTPInfo>>(Resource.loading())
    val otpState: StateFlow<Resource<OTPInfo>> get() = _otpState

    private val _otpUIState = MutableStateFlow(OTPUIState())
    val otpUIState: StateFlow<OTPUIState> get() = _otpUIState

    private fun validateEmail(): Boolean {
        _preotpUIState.value = preotpUIState.value.copy(emailError = "")

        var isValid = true
        if (!preotpUIState.value.email.isValidEmail()) {
            _preotpUIState.value =
                preotpUIState.value.copy(emailError = resourcesProvider.getString(R.string.otp_error_entermail))
            isValid = false
        }
        return isValid
    }

    fun changeEmailValue(text: String) {
        _preotpUIState.value = preotpUIState.value.copy(
            email = text,
        )
    }

    fun sendEmail() {
        if (!validateEmail()) return
        _preotpUIState.value = preotpUIState.value.copy(isLoading = true)

        viewModelScope.launch(Dispatchers.Main) {
            delay(200)
            val preOTPResponse = preOTPUseCase(
                PreOTPRequest(
                    email = preotpUIState.value.email
                )
            )
            _preotpUIState.value = preotpUIState.value.copy(isLoading = preOTPResponse.isLoading())

            _preOTPState.tryEmit(preOTPResponse)
            Log.d("check_response", "--- $preOTPResponse")
        }
    }

    fun clearStatePreOTP() {
        _preOTPState.value = Resource.loading()
    }

    fun changeOTPFirstTextValue(text: String) {
        _otpUIState.value = otpUIState.value.copy(
            firstText = text
        )
    }

    fun changeOTPSecondTextValue(text: String) {
        _otpUIState.value = otpUIState.value.copy(
            secondText = text
        )
    }

    fun changeOTPThirdTextValue(text: String) {
        _otpUIState.value = otpUIState.value.copy(
            thirdText = text
        )
    }

    fun changeOTPForthTextValue(text: String) {
        _otpUIState.value = otpUIState.value.copy(
            forthText = text
        )
    }

    private fun validateOTP(): Boolean {
        _otpUIState.value = otpUIState.value.copy(textFieldError = "")
        var isValid = true

        if (_otpUIState.value.firstText.isEmpty() || _otpUIState.value.secondText.isEmpty() || _otpUIState.value.thirdText.isEmpty() || _otpUIState.value.forthText.isEmpty()) {
            _otpUIState.value =
                otpUIState.value.copy(textFieldError = resourcesProvider.getString(R.string.otp_error_fill))
            isValid = false
        }
        return isValid
    }

    fun sendOTP(email: String) {
        if (!validateOTP()) return
        else {
            _otpUIState.value = otpUIState.value.copy(isLoading = true)

            viewModelScope.launch(Dispatchers.Main) {
                delay(200)

                val response = otpUseCase(
                    OTPRequest(
                        email = email,
                        otp = otpUIState.value.firstText + otpUIState.value.secondText + otpUIState.value.thirdText + otpUIState.value.forthText
                    )
                )
                _otpUIState.value = otpUIState.value.copy(isLoading = response.isLoading())
                _otpState.tryEmit(response)

                Log.d(
                    "check_response", "--- $response"
                )
            }
        }
    }

    fun clearStateOTP() {
        _otpState.value = Resource.loading()
    }
}



