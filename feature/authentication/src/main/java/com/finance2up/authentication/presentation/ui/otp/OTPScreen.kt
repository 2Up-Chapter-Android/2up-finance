package com.finance2up.authentication.presentation.ui.otp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.finance2up.authentication.R
import com.finance2up.authentication.presentation.util.fontSizeDimensionResource

@Composable
fun OTPScreen(navController: NavController, emailUser: String?) {
    val otpViewModel: OTPViewModel = hiltViewModel()

    val firstText = otpViewModel.firstText.collectAsStateWithLifecycle()
    val secondText = otpViewModel.secondText.collectAsStateWithLifecycle()
    val thirdText = otpViewModel.thirdText.collectAsStateWithLifecycle()
    val forthText = otpViewModel.forthText.collectAsStateWithLifecycle()
    val oTPUIState = otpViewModel.oTPUIState.collectAsStateWithLifecycle()
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

            val painter: Painter = painterResource(id = R.drawable.logo_otp)
            Image(
                painter = painter, contentDescription = "My Image", modifier = Modifier
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
                text = "($emailUser)",
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

            val focusRequester1 = remember { FocusRequester() }
            val focusRequester2 = remember { FocusRequester() }
            val focusRequester3 = remember { FocusRequester() }
            val focusRequester4 = remember { FocusRequester() }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(id = R.dimen.paddingTop_otp_textField)
                    ), horizontalArrangement = Arrangement.Center
            ) {
                TextFieldForOTP1(
                    firstText.value,
                    { otpViewModel.changeOTPFirstTextValue(it) },
                    focusRequester1,
                    focusRequester2
                )
                TextFieldForOTP1(
                    secondText.value,
                    { otpViewModel.changeOTPSecondTextValue(it) },
                    focusRequester2,
                    focusRequester3
                )
                TextFieldForOTP1(
                    thirdText.value,
                    { otpViewModel.changeOTPThirdTextValue(it) },
                    focusRequester3,
                    focusRequester4
                )
                TextFieldForOTP1(
                    value = forthText.value,
                    onValueChange = { otpViewModel.changeOTPForthTextValue(it) },
                    focusRequester = focusRequester4,
                    nextFocusRequester = focusRequester4,
                    imeAction = ImeAction.Done
                )
            }
            AnimatedVisibility(
                visible = oTPUIState.value.visibilityError
            ) {
                ErrorText(
                    text = oTPUIState.value.textFieldError
                )
            }
            Button(
                onClick = {
                    otpViewModel.sendOTP(navController)
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(id = R.dimen.paddingTop_otp_button)
                    ),
//                enabled = oTPUIState.value.visibilityError
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

fun onTextChange(newValue: String, onValueChange: (String) -> Unit) {
    if (newValue.length <= 1) {
        if (newValue.all { it.isDigit() }) {
            onValueChange(newValue)
        }
    }
}

@Composable
fun TextFieldForOTP1(
    value: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester,
    nextFocusRequester: FocusRequester,
    imeAction: ImeAction = ImeAction.Default
) {
    TextField(
        value = value,
        onValueChange = {it: String ->
            onTextChange(it, onValueChange)
        },
        modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.paddingStart_otp_textField)
            )
            .width(
                dimensionResource(id = R.dimen.width_otp_textField)
            )
            .height(dimensionResource(id = R.dimen.height_otp_textField))

            .focusRequester(focusRequester),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = imeAction
        ),
        maxLines = 1,
    )
    LaunchedEffect(value) {
        if (value.length == 1) {
            nextFocusRequester.requestFocus()
        }
    }
}