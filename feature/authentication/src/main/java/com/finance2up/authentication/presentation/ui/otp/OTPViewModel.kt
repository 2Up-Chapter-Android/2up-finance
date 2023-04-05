package com.finance2up.authentication.presentation.ui.otp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aibles.finance.data.remote.util.Resource
import com.aibles.finance.presentation.utils.ResourcesProvider
import com.aibles.finance.utils.isValidEmail
import com.finance2up.authentication.R
import com.finance2up.authentication.domain.entity.otp.EmailInfo
import com.finance2up.authentication.domain.entity.otp.OTPInfo
import com.finance2up.authentication.domain.entity.otp.OTPRequest
import com.finance2up.authentication.domain.entity.otp.EmailRequest
import com.finance2up.authentication.domain.usecase.SendOTPUseCase
import com.finance2up.authentication.domain.usecase.SendEmailUsecase
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
    private val sendEmailUsecase: SendEmailUsecase,
    private val sendOTPUseCase: SendOTPUseCase

) : ViewModel() {


    private val _otpSendState = MutableStateFlow<Resource<OTPInfo>>(Resource.loading())
    val otpSendState: StateFlow<Resource<OTPInfo>> get() = _otpSendState

    private val _emailSendState = MutableStateFlow<Resource<EmailInfo>>(Resource.loading())
    val emailSendState: StateFlow<Resource<EmailInfo>> get() = _emailSendState

    private val _otpUIState = MutableStateFlow(OTPUIState())
    val otpUIState: StateFlow<OTPUIState> get() = _otpUIState

    private fun validateEmail(): Boolean {
        _otpUIState.value = otpUIState.value.copy(emailError = "")

        var isValid = true
        if (!otpUIState.value.email.isValidEmail()) {
            _otpUIState.value =
                otpUIState.value.copy(emailError = resourcesProvider.getString(R.string.otp_error_entermail))
            isValid = false
        }
        return isValid
    }

    fun changeEmailValue(text: String) {
        _otpUIState.value = otpUIState.value.copy(
            email = text,
        )
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

    fun sendOTP() {
        if (!validateEmail()) return
        else {
            _otpUIState.value = otpUIState.value.copy(isLoading = true)

            viewModelScope.launch(Dispatchers.Main) {
                val response = sendOTPUseCase(
                    OTPRequest(
                        email = otpUIState.value.email,
                        otp = otpUIState.value.firstText + otpUIState.value.secondText + otpUIState.value.thirdText + otpUIState.value.forthText
                    )
                )
                _otpUIState.value = otpUIState.value.copy(isLoading = response.isLoading())
                _otpSendState.tryEmit(response)

                Log.d(
                    "check_response", "--- $response"
                )
            }
        }
    }

    fun resendEmail() {
        if (!validateEmail()) return
        _otpUIState.value = otpUIState.value.copy(isLoading = true)

        viewModelScope.launch(Dispatchers.Main) {
            val response = sendEmailUsecase(
                EmailRequest(
                    email = otpUIState.value.email
                )
            )
            _otpUIState.value = otpUIState.value.copy(isLoading = response.isLoading())

            _emailSendState.tryEmit(response)
            Log.d("check_response", "--- $response")
        }
    }

    fun clearStateOTP() {
        _otpSendState.value = Resource.loading()
    }
    fun clearStateEmail() {
        _emailSendState.value = Resource.loading()
    }
}



