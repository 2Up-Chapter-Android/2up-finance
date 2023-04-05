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
//    Đây là cách viết count down timer trong compose, cần để trong khối remember để nó vẫn giữ giá trị khi recompose
    val countDownTimer = remember{
        object : CountDownTimer(61_000, 1000) {

            // Callback function, fired on regular interval
            override fun onTick(millisUntilFinished: Long) {
                Log.d("OTPTImer", millisUntilFinished.toString())
            }

            // Callback function, fired when the time is up
            override fun onFinish() {
                isVisibleResendButton.value = true
            }
        }
    }

//    Vừa vào màn nhập OTP thì timer chạy
//    Lưu ý LaunchEffect mới khiến hàm bên trong gọi 1 lần, không phải SideEffect.
//    Trước đây em nói code trong SideEffect chỉ gọi 1 lần là sai. Code trong Launched Effect mới được gọi 1 lần
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
                    .padding(
                        bottom = dimensionResource(id = R.dimen.paddingBottom_otp_image)
                    ),
            )
            TextFieldEnterEmail(
                text = otpUIState.value.email,
                onTextChange = { otpViewModel.changeEmailValue(it) },
                keyboardOption = KeyboardOptions(imeAction = ImeAction.Next),
                hint = stringResource(id = R.string.otp_hint_email),
                trailingIcon = {
                    IconButton(onClick = { otpViewModel.changeEmailValue("") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_clear),
                            contentDescription = "",
                            tint = LocalContentColor.current.copy(alpha = 1f),
                        )
                    }
                },
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

            AnimatedVisibility(visible = otpUIState.value.visibilityEmailError) {
                ErrorText(
                    text = otpUIState.value.emailError
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
                            Log.d("OTPTImer", "Click Resend")
                            otpViewModel.resendEmail()
//                            Ấn resend thì lại phải ẩn nút resend và timer phải đếm lại
                            isVisibleResendButton.value = false
                            countDownTimer.start()
                        })
            }
            Button(
                onClick = {
                    otpViewModel.sendOTP()
//                    Cancel để khi chuyển màn timer không đếm nữa, tránh memory leak
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

//    Đoạn này nên đổi thành LaunchEffect, vì lý do em nói ở trên
    SideEffect {
        if (otpSendState.value.isSuccessful()) {
            Toast.makeText(
                context, otpSendState.value.data?.statusMessage, Toast.LENGTH_SHORT
            ).show()
            otpViewModel.clearStateOTP()
            navController.navigate(route = "LoginScreen")
        } else if (otpSendState.value.isError() && otpSendState.value.error != null) {
//            TODO: Đây là cách lấy ra error message và error code
            val msg = emailSendState.value.error?.errorData?.detail ?: "nothing"
            val errorCode = emailSendState.value.error?.errorData?.code ?: "nothing"
            val errorMessage = emailSendState.value.error?.message

            Log.d("OTPScreenLog", "else: $errorMessage")
            Toast.makeText(
                context, errorMessage, Toast.LENGTH_SHORT
            ).show()
            if (errorMessage == "OTP has expired") {
                otpUIState.value.visibilityOTPExpiredError = true
            }
        }
        if (emailSendState.value.isSuccessful()) {
            Toast.makeText(
                context, emailSendState.value.data?.statusMessage, Toast.LENGTH_SHORT
            ).show()
            otpViewModel.clearStateEmail()
        } else if (emailSendState.value.isError() && emailSendState.value.error != null) {
            val errorMessage = emailSendState.value.error?.message
            Log.d("OTPScreenLog", "else: $errorMessage")
            Toast.makeText(
                context, errorMessage, Toast.LENGTH_SHORT
            ).show()
//            if (emailSendState.value.error?.) { case user activated
//                navController.navigate(route = "LoginScreen")
//            }
        }
    }
}

@Composable
fun TextFieldEnterEmail(
    text: String,
    onTextChange: (String) -> Unit,
    keyboardOption: KeyboardOptions,
    hint: String,
    trailingIcon: @Composable () -> Unit,

    ) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(
            topEnd = dimensionResource(id = R.dimen.cornerRadius_otp_textField),
            bottomStart = dimensionResource(id = R.dimen.cornerRadius_otp_textField)
        ),
        value = text,
        onValueChange = { onTextChange(it) },
        label = {
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = hint,
                color = Color.Black,
                fontSize = fontSizeDimensionResource(id = R.dimen.textSize_otp_textField)
            )
        },
        textStyle = TextStyle(
            fontSize = fontSizeDimensionResource(id = R.dimen.textSize_otp_textField)
        ),
        trailingIcon = {
            if (text.isNotEmpty()) {
                trailingIcon()
            }
        },
        singleLine = true,
        keyboardOptions = keyboardOption,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Black
        )
    )
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
