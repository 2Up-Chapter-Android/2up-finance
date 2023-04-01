package com.finance2up.authentication.presentation.ui.otp

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.finance2up.authentication.R
import com.finance2up.authentication.presentation.util.fontSizeDimensionResource

@Composable
fun PreOTPScreen(navController: NavController) {
    val otpViewModel: OTPViewModel = hiltViewModel()
    val preotpUIState = otpViewModel.preotpUIState.collectAsStateWithLifecycle()
    val preotpState = otpViewModel.preOTPState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    SideEffect {
        if (preotpState.value.isSuccessful()) Toast.makeText(
            context, "Send email success", Toast.LENGTH_SHORT
        ).show()
//        else Toast.makeText(
//            context, preotpState.value.error?.message, Toast.LENGTH_SHORT
//        ).show()

    }
    Column(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_preotp_parentView))
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            PreOTPEditText(
                text = preotpUIState.value.email,
                onTextChange = { otpViewModel.changeEmailValue(it) },
                keyboardOption = KeyboardOptions(imeAction = ImeAction.Next),
                hint = stringResource(id = R.string.preotp_hint_email),
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
            AnimatedVisibility(visible = preotpUIState.value.visibilityEmailError) {
                EmailErrorText(
                    text = preotpUIState.value.emailError
                )
            }
            Button(
                onClick = {
                    otpViewModel.sendEmail()
                },
                enabled = preotpUIState.value.enableSendEmailButton,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.paddingTop_preOtp_button)),

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
            if (preotpState.value.isSuccessful()) {
                navController.navigate(route = "OTPScreen/${preotpUIState.value.email}")
            }
        }
    }
}

@Composable
fun PreOTPEditText(
    text: String,
    onTextChange: (String) -> Unit,
    keyboardOption: KeyboardOptions,
    hint: String,
    trailingIcon: @Composable () -> Unit,

    ) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(
            topEnd = dimensionResource(id = R.dimen.cornerRadius_preotp_textField),
            bottomStart = dimensionResource(id = R.dimen.cornerRadius_preotp_textField)
        ),
        value = text,
        onValueChange = { onTextChange(it) },
        label = {
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = hint,
                color = Color.Black,
                fontSize = fontSizeDimensionResource(id = R.dimen.textSize_preotp_textField)
            )
        },
        textStyle = TextStyle(
            fontSize = fontSizeDimensionResource(id = R.dimen.textSize_preotp_textField)
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
fun EmailErrorText(text: String) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.marginTop_preotp_errorText)))

        Text(
            text = text,
            fontSize = fontSizeDimensionResource(id = R.dimen.textSize_preOtp_errorText),
            color = Color.Red
        )
    }
}