package com.finance2up.authentication.presentation.ui.otp

import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.finance2up.authentication.R
import com.finance2up.authentication.presentation.util.fontSizeDimensionResource
import com.finance2up.authentication.presentation.util.formatDuration
import kotlinx.coroutines.delay


@SuppressLint("UnrememberedMutableState")
@Composable
fun OTPScreen(navController: NavController) {
    val otpViewModel: OTPViewModel = hiltViewModel()

    val otpUIState = otpViewModel.otpUIState.collectAsStateWithLifecycle()
    val otpSendState = otpViewModel.otpSendState.collectAsState()

    val emailSendState = otpViewModel.emailSendState.collectAsState()

    val context = LocalContext.current

    val isVisibleResendButton = remember {
        mutableStateOf(false)
    }
    val countDownTimer = remember {
        object : CountDownTimer(61_000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                isVisibleResendButton.value = true
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        countDownTimer.start()
    }

    Column(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_otp_parentView))
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {

            Text(
                stringResource(R.string.otp_title), style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = fontSizeDimensionResource(id = R.dimen.textSize_otp_title)
                )
            )
            Text(
                stringResource(R.string.otp_enterInput),
                style = MaterialTheme.typography.h5.copy(
                    fontSize = fontSizeDimensionResource(id = R.dimen.textSize_otp_checkEmail)
                ),
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.paddingTop_otp_checkEmail))
            )

            Image(
                painter = painterResource(id = R.drawable.logo_otp),
                contentDescription = "My Image",
                modifier = Modifier
                    .width(
                        dimensionResource(id = R.dimen.width_otp_imageOTP)
                    )
                    .height(
                        dimensionResource(id = R.dimen.height_otp_imageOTP)
                    )
                    .padding(
                        top = dimensionResource(id = R.dimen.paddingTop_otp_imageOTP)
                    )
                    .align(Alignment.CenterHorizontally)

            )

            Text(
                text = "abc@gmail.com",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.paddingTop_otp_email))
                    .wrapContentWidth(align = Alignment.CenterHorizontally),
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Bold, fontSize = fontSizeDimensionResource(
                        id = R.dimen.textSize_otp_email
                    )
                )
            )

            val listenRequestFirstTextField = remember { FocusRequester() }
            val listenRequestSecondTextField = remember { FocusRequester() }
            val listenRequestThirdTextField = remember { FocusRequester() }
            val listenRequestForthTextField = remember { FocusRequester() }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(id = R.dimen.paddingTop_otp_textField)
                    ), horizontalArrangement = Arrangement.Center
            ) {
                TextFieldEnterOTP(
                    value = otpUIState.value.firstText,
                    onValueChange = { otpViewModel.changeOTPFirstTextValue(it) },
                    focusRequester = listenRequestFirstTextField,
                    nextFocusRequester = listenRequestSecondTextField
                )
                TextFieldEnterOTP(
                    value = otpUIState.value.secondText,
                    onValueChange = { otpViewModel.changeOTPSecondTextValue(it) },
                    focusRequester = listenRequestSecondTextField,
                    nextFocusRequester = listenRequestThirdTextField
                )
                TextFieldEnterOTP(
                    value = otpUIState.value.thirdText,
                    onValueChange = { otpViewModel.changeOTPThirdTextValue(it) },
                    focusRequester = listenRequestThirdTextField,
                    nextFocusRequester = listenRequestForthTextField
                )
                TextFieldEnterOTP(
                    value = otpUIState.value.forthText,
                    onValueChange = { otpViewModel.changeOTPForthTextValue(it) },
                    focusRequester = listenRequestForthTextField,
                    nextFocusRequester = listenRequestForthTextField,
                )
            }

            AnimatedVisibility(visible = isVisibleResendButton.value) {
                Text(stringResource(R.string.otp_resendOTP),
                    style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Magenta,
                        fontSize = fontSizeDimensionResource(
                            id = R.dimen.textSize_otp_resendOTP
                        )
                    ),
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.paddingTop_otp_resendOTP))
                        .clickable {
                            otpViewModel.resendEmail()
                            isVisibleResendButton.value = false
                            countDownTimer.start()
                        })
            }
            Button(
                onClick = {
                    otpViewModel.sendOTP()
                    countDownTimer.cancel()
                },
                enabled = otpUIState.value.enableActiveButton,

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(id = R.dimen.paddingTop_otp_button)
                    ),
            ) {
                Text(
                    stringResource(R.string.otp_active), modifier = Modifier.padding(
                        vertical = dimensionResource(id = R.dimen.paddingVertical_otp_textButton)
                    ), style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.Bold, fontSize = fontSizeDimensionResource(
                            id = R.dimen.textSize_otp_textButton
                        )
                    )
                )
            }
        }
    }

    SideEffect {
        if (otpSendState.value.isSuccessful()) {
            Toast.makeText(
                context, otpSendState.value.data?.statusMessage, Toast.LENGTH_SHORT
            ).show()

            otpViewModel.clearStateOTP()
            navController.navigate(route = "LoginScreen")

        } else if (otpSendState.value.isError() && otpSendState.value.error != null) {

            when (otpSendState.value.error?.errorData?.code ?: "nothing") {
                "org.up.finance.exception.OtpNotFoundException" -> {
                    Toast.makeText(
                        context, R.string.otp_error_expired, Toast.LENGTH_SHORT
                    ).show()
                    isVisibleResendButton.value = true
                }
                "org.up.finance.exception.OtpBadRequestException" -> {
                    Toast.makeText(
                        context, R.string.otp_error_fillOtp, Toast.LENGTH_SHORT
                    ).show()
                }
                "org.up.finance.exception.EmailNotFoundException" -> {
                    Toast.makeText(
                        context, R.string.otp_error_email_existed, Toast.LENGTH_SHORT
                    ).show()
                }
                "org.up.finance.exception.UserActivatedException" -> {
                    Toast.makeText(
                        context, R.string.otp_error_activated, Toast.LENGTH_SHORT
                    ).show()
                    otpViewModel.clearStateOTP()
                    navController.navigate(route = "LoginScreen")
                }
                "org.up.finance.exception.xxx.MethodArgumentNotValidException" -> {
                    Toast.makeText(
                        context, R.string.otp_error_format, Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        if (emailSendState.value.isSuccessful()) {
            Toast.makeText(
                context, emailSendState.value.data?.statusMessage, Toast.LENGTH_SHORT
            ).show()
            otpViewModel.clearStateEmail()
        } else if (emailSendState.value.isError() && emailSendState.value.error != null) {

            when (emailSendState.value.error?.errorData?.code ?: "nothing") {
                "org.up.finance.exception.UserActivatedException" -> {
                    Toast.makeText(
                        context, R.string.otp_error_activated, Toast.LENGTH_SHORT
                    ).show()
                    otpViewModel.clearStateEmail()
                    navController.navigate(route = "LoginScreen")
                }
                "org.up.finance.exception.EmailNotFoundException" -> {
                    Toast.makeText(
                        context, R.string.otp_error_email_existed, Toast.LENGTH_SHORT
                    ).show()
                }
                "org.up.finance.exception.xxx.MethodArgumentNotValidException" -> {
                    Toast.makeText(
                        context, R.string.otp_error_format, Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}

@Composable
fun ErrorText(text: String) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.marginTop_otp_errorText))
                .padding(top = dimensionResource(id = R.dimen.paddingTop_otp_errorText))
        )

        Text(
            text = text, color = Color.Red
        )
    }
}

@Composable
fun TextFieldEnterOTP(
    value: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester,
    nextFocusRequester: FocusRequester,
) {
    TextField(
        value = value, onValueChange = { it: String ->
            if (it.length <= 1) {
                if (it.all { it.isDigit() }) {
                    onValueChange(it)
                }
            }
        }, modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.paddingStart_otp_textField)
            )
            .width(
                dimensionResource(id = R.dimen.width_otp_textField)
            )
            .height(dimensionResource(id = R.dimen.height_otp_textField))

            .focusRequester(focusRequester), keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ), singleLine = true, textStyle = TextStyle(fontWeight = FontWeight.Bold)
    )
    LaunchedEffect(value) {
        if (value.length == 1) {
            nextFocusRequester.requestFocus()
        }
    }
}
