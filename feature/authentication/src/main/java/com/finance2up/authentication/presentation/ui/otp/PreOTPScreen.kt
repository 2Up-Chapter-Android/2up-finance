package com.finance2up.authentication.presentation.ui.otp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.finance2up.authentication.R
import com.finance2up.authentication.presentation.util.fontSizeDimensionResource


@Composable
fun PreOTPScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.paddingHorizontal_login_parentView))
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {

            var emailUser by remember { mutableStateOf(TextFieldValue("")) }
            var isEmailValid by remember { mutableStateOf(false) }

            TextField(
                value = emailUser,
                onValueChange = { newValue ->
                    emailUser = newValue
                    isEmailValid = isValidEmail(newValue.text)
                },
                isError = !isEmailValid,
                label = {
                    Text(
                        modifier = Modifier
                            .alpha(ContentAlpha.medium),
                        text = stringResource(R.string.preotp_hint_email),
                        color = Color.Black,
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.paddingTop_preOtp_textField))
            )

            if (emailUser.toString() != "" && !isEmailValid) AnimatedVisibility(visible = true) {
                EmailErrorText(
                    text = stringResource(R.string.otp_error_entermail)
                )
            }

            Button(
                onClick = {
                    if (isEmailValid) navController.navigate(route = "OTPScreen/${emailUser.text}")
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.paddingTop_preOtp_button))
            ) {
                Text(
                    stringResource(R.string.preotp_sendEmail),
                    modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.paddingVertical_preOtp_textButton)),
                    style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = fontSizeDimensionResource(id = R.dimen.textSize_preOtp_textButton)
                    )
                )
            }
        }
    }
}

private fun isValidEmail(email: String): Boolean {
    val emailRegex =
        Regex("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})\$")
    return emailRegex.matches(email)
}

@Composable
fun EmailErrorText(text: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            color = Color.Red
        )
    }
}