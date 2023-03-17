package com.finance2up.authentication.presentation.ui.otp

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.finance2up.authentication.R
import com.finance2up.authentication.presentation.util.fontSizeDimensionResource

@Composable
fun OTPScreen(navController: NavController, emailUser: String?) {
    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.paddingHorizontal_login_parentView))
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

            val (firstText, onFirstTextChange) = remember { mutableStateOf("") }
            val (secondText, onSecondTextChange) = remember { mutableStateOf("") }
            val (thirdText, onThirdTextChange) = remember { mutableStateOf("") }
            val (fourthText, onFourthTextChange) = remember { mutableStateOf("") }
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

                TextFieldForOTP(
                    firstText, onFirstTextChange, focusRequester1, focusRequester2
                )
                TextFieldForOTP(
                    secondText, onSecondTextChange, focusRequester2, focusRequester3
                )
                TextFieldForOTP(
                    thirdText, onThirdTextChange, focusRequester3, focusRequester4
                )
                TextFieldForOTP(
                    fourthText, onFourthTextChange, focusRequester4, focusRequester4
                )
            }
            if (firstText == "" || secondText == "" || thirdText == "" || fourthText == "") ErrorText(
                text = stringResource(R.string.otp_error_fill)
            )
            else {
                Button(
                    onClick = {
                        navController.navigate("LoginScreen")
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = dimensionResource(id = R.dimen.paddingTop_otp_button)
                        )
                ) {
                    Text(
                        stringResource(R.string.otp_submit), modifier = Modifier.padding(
                            vertical = dimensionResource(id = R.dimen.paddingTop_otp_button)
                        ), style = MaterialTheme.typography.h5.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = fontSizeDimensionResource(
                                id = R.dimen.textSize_otp_textButton
                            )
                        )
                    )
                }
            }

        }
    }
}

@Composable
fun ErrorText(text: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = dimensionResource(id = R.dimen.paddingStart_otp_textField)
            )
    ) {
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
fun TextFieldForOTP(
    value: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester,
    nextFocusRequester: FocusRequester
) {
    TextField(
        value = value,
        onValueChange = { onTextChange(it, onValueChange) },
        modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.paddingStart_otp_textField)
            )
            .width(
                dimensionResource(id = R.dimen.width_otp_textField)
            )
            .height(dimensionResource(id = R.dimen.height_otp_textField))

            .focusRequester(focusRequester),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxLines = 1,
    )
    LaunchedEffect(value) {
        if (value.length == 1) {
            nextFocusRequester.requestFocus()
        }
    }
}

