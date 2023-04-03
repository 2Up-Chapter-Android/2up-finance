package com.finance2up.authentication.presentation.ui.otp

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.finance2up.authentication.R
import com.finance2up.authentication.presentation.util.fontSizeDimensionResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("UnrememberedMutableState")
@Composable
fun OTPScreen(navController: NavController) {
    val otpViewModel: OTPViewModel = hiltViewModel()

    val otpUIState = otpViewModel.otpUIState.collectAsStateWithLifecycle()
    val preotpUIState = otpViewModel.preotpUIState.collectAsStateWithLifecycle()
    val otpState = otpViewModel.otpState.collectAsState()
    val context = LocalContext.current

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
                stringResource(R.string.otp_checkEmail),
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
                text = preotpUIState.value.email,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.width_otp_textField))
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
                TextFieldForOTP(
                    value = otpUIState.value.firstText,
                    onValueChange = { otpViewModel.changeOTPFirstTextValue(it) },
                    focusRequester = listenRequestFirstTextField,
                    nextFocusRequester = listenRequestSecondTextField
                )
                TextFieldForOTP(
                    value = otpUIState.value.secondText,
                    onValueChange = { otpViewModel.changeOTPSecondTextValue(it) },
                    focusRequester = listenRequestSecondTextField,
                    nextFocusRequester = listenRequestThirdTextField
                )
                TextFieldForOTP(
                    value = otpUIState.value.thirdText,
                    onValueChange = { otpViewModel.changeOTPThirdTextValue(it) },
                    focusRequester = listenRequestThirdTextField,
                    nextFocusRequester = listenRequestForthTextField
                )
                TextFieldForOTP(
                    value = otpUIState.value.forthText,
                    onValueChange = { otpViewModel.changeOTPForthTextValue(it) },
                    focusRequester = listenRequestForthTextField,
                    nextFocusRequester = listenRequestForthTextField,
                )
            }
            AnimatedVisibility(
                visible = otpUIState.value.visibilityError
            ) {
                ErrorText(
                    text = otpUIState.value.textFieldError
                )
            }
            Button(
                onClick = {
                    otpViewModel.sendOTP("ngoc@gmail.com")
                },
                enabled = otpUIState.value.enableSubmitButton,

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(id = R.dimen.paddingTop_otp_button)
                    ),
            ) {
                Text(
                    stringResource(R.string.otp_submit), modifier = Modifier.padding(
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
        if (otpState.value.isSuccessful()) {
            Toast.makeText(
                context, otpState.value.data?.statusMessage, Toast.LENGTH_SHORT
            ).show()
            otpViewModel.clearStateOTP()
            navController.navigate(route = "LoginScreen")
        } else if (otpState.value.isError() && otpState.value.error != null) {
            Log.d("OTPScreenLog", "else: ${otpState.value.error?.message}")
            Toast.makeText(
                context, otpState.value.error?.message, Toast.LENGTH_SHORT
            ).show()
        }
    }
}

@Composable
fun ErrorText(text: String) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.marginTop_otp_errorText)))

        Text(
            text = text, color = Color.Red
        )
    }
}

@Composable
fun TextFieldForOTP(
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
