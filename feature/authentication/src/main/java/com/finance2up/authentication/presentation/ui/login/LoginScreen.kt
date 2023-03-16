package com.finance2up.authentication.presentation.ui.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.finance2up.authentication.R
import com.finance2up.authentication.presentation.util.fontSizeDimensionResource

@Composable
fun LoginScreen() {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val temp = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(id = R.dimen.paddingHorizontal_login_parentView))
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { focusManager.clearFocus() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.image_welcome_login),
                contentDescription = "",
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.width_login_welcomeImage))
                    .height(dimensionResource(id = R.dimen.height_login_welcomeImage))
                    .padding(vertical = dimensionResource(id = R.dimen.paddingVertical_login_welcomeImage)),
            )

            Text(
                text = stringResource(id = R.string.login_welcomeTitle),
                fontSize = fontSizeDimensionResource(id = R.dimen.textSize_login_welcomeTitle)
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.marginTop_login_usernameTextField)))
            LoginEditText(
                text = "kakrfa;lkwr",
                onTextChange = { },
                keyboardOption = KeyboardOptions(imeAction = ImeAction.Next),
                trailingIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_clear),
                            contentDescription = "",
                            tint = LocalContentColor.current.copy(alpha = 1f),
                        )
                    }
                },
                hint = stringResource(id = R.string.login_hint_username)
            )
            AnimatedVisibility(visible = true) { LoginErrorText(text = stringResource(id = R.string.login_error_notExistAccount)) }

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.marginTop_login_passwordTextField)))
            LoginPasswordEditText(
                text = "",
                onTextChange = {},
                imeAction = ImeAction.Done
            )
            AnimatedVisibility(visible = true) { LoginErrorText(text = stringResource(id = R.string.login_error_incorrectPassword)) }

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.marginTop_login_forgotPassTextButton)))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                Text(
                    modifier = Modifier
                        .clickable(interactionSource = interactionSource, indication = null) { },
                    text = stringResource(id = R.string.login_forgotPassword),
                    fontSize = fontSizeDimensionResource(id = R.dimen.textSize_login_forgotPasswordTextButton),
                    color = colorResource(id = R.color.login_textButton),
                    textAlign = TextAlign.End
                )
            }

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.marginTop_login_loginButton)))
            Button(
                onClick = { temp.value = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.height_login_loginButton)),
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.cornerRadius_login_loginButton)),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.login_loginButton))
            ) {
                Text(
                    text = stringResource(id = R.string.all_login),
                    fontSize = fontSizeDimensionResource(id = R.dimen.textSize_login_loginButton),
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.marginTop_login_progressBar)))
            AnimatedVisibility(visible = temp.value) {
                CircularProgressIndicator(
                    modifier = Modifier.size(dimensionResource(id = R.dimen.size_login_progressBar)),
                    color = colorResource(id = R.color.login_progressBar),
                    strokeWidth = dimensionResource(id = R.dimen.progressBarStrokeWidth_login)
                )
            }
        }

        Column {
            Text(
                text = buildAnnotatedString {
                    append(stringResource(id = R.string.login_dontHaveAccount))
                    append(" ")
                    withStyle(SpanStyle(color = colorResource(id = R.color.login_textButton))) {
                        append(
                            stringResource(id = R.string.all_signup)
                        )
                    }
                },
                fontSize = fontSizeDimensionResource(id = R.dimen.textSize_login_registerTextButton),
                modifier = Modifier
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) { }
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.marginBottom_login_registerTextButton)))
        }
    }
}

@Composable
fun LoginEditText(
    text: String,
    onTextChange: (String) -> Unit,
    keyboardOption: KeyboardOptions,
    trailingIcon: @Composable () -> Unit,
    hint: String
) {
    val textValue = remember { mutableStateOf("") }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.height_login_loginTextField))
            .border(
                width = 1.dp,
                color = Color.Black,
                RoundedCornerShape(
                    topEnd = dimensionResource(id = R.dimen.cornerRadius_login_loginTextField),
                    bottomStart = dimensionResource(id = R.dimen.cornerRadius_login_loginTextField)
                )
            )
            .padding(end = dimensionResource(id = R.dimen.paddingEnd_login_loginTextField)),
        value = textValue.value,
        onValueChange = {
//            onTextChange(it)
            textValue.value = it
        },
        label = {
            Text(
                modifier = Modifier
                    .alpha(ContentAlpha.medium),
                text = hint,
                color = Color.Black,
                fontSize = fontSizeDimensionResource(id = R.dimen.textSize_login_loginTextField)
            )
        },
        textStyle = TextStyle(
            fontSize = fontSizeDimensionResource(id = R.dimen.textSize_login_loginTextField)
        ),
        singleLine = true,
        trailingIcon = {
            if (textValue.value.isNotEmpty()) {
                trailingIcon()
            }
        },
        keyboardOptions = keyboardOption,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun LoginPasswordEditText(
    text: String,
    onTextChange: (String) -> Unit,
    imeAction: ImeAction,
) {
    val password = rememberSaveable { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }

    val icon = if (passwordVisibility.value)
        painterResource(id = R.drawable.ic_hide_password)
    else
        painterResource(id = R.drawable.ic_show_password)

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.height_login_loginTextField))
            .border(
                width = 1.dp,
                color = Color.Black,
                RoundedCornerShape(
                    topEnd = dimensionResource(id = R.dimen.cornerRadius_login_loginTextField),
                    bottomStart = dimensionResource(id = R.dimen.cornerRadius_login_loginTextField)
                )
            )
            .padding(end = dimensionResource(id = R.dimen.paddingEnd_login_loginTextField)),
        value = password.value,
        onValueChange = {
//            onTextChange(it)
            password.value = it
        },
        label = {
            Text(
                modifier = Modifier
                    .alpha(ContentAlpha.medium),
                text = stringResource(id = R.string.login_hint_password),
                color = Color.Black,
                fontSize = fontSizeDimensionResource(id = R.dimen.textSize_login_loginTextField)
            )
        },
        textStyle = TextStyle(
            fontSize = fontSizeDimensionResource(id = R.dimen.textSize_login_loginTextField)
        ),
        singleLine = true,
        trailingIcon = {
            if (password.value.isNotEmpty()) {
                IconButton(onClick = {
                    passwordVisibility.value = !passwordVisibility.value
                }) {
                    Icon(
                        painter = icon,
                        contentDescription = "",
                        tint = LocalContentColor.current.copy(alpha = 1f)
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        visualTransformation = if (passwordVisibility.value) VisualTransformation.None
        else PasswordVisualTransformation(mask = '*')
    )

}

@Composable
fun LoginErrorText(text: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = dimensionResource(id = R.dimen.paddingStart_login_loginErrorText))
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.marginTop_login_loginErrorText)))
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = fontSizeDimensionResource(id = R.dimen.textSize_login_loginErrorText),
            color = colorResource(id = R.color.login_errorText)
        )
    }
}
