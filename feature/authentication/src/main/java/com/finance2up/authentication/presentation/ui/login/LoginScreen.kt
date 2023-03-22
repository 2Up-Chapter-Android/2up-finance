package com.finance2up.authentication.presentation.ui.login

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.finance2up.authentication.R
import com.finance2up.authentication.presentation.util.fontSizeDimensionResource

@Composable
fun LoginScreen() {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }
    val viewModel: LoginViewModel = hiltViewModel()
    val usernameInput = viewModel.usernameInput.collectAsStateWithLifecycle()
    val passwordInput = viewModel.passwordInput.collectAsStateWithLifecycle()
    val loginState = viewModel.loginState.collectAsStateWithLifecycle()
    val loginUIState = viewModel.loginUiState.collectAsStateWithLifecycle()

    if (loginState.value.isSuccessful()) Toast.makeText(
        context,
        "Login Success",
        Toast.LENGTH_SHORT
    ).show()

    Box {
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
                    text = usernameInput.value,
                    onTextChange = { viewModel.onUsernameValueChange(it) },
                    keyboardOption = KeyboardOptions(imeAction = ImeAction.Next),
                    trailingIcon = {
                        IconButton(onClick = { viewModel.onUsernameValueChange("") }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_clear),
                                contentDescription = "",
                                tint = LocalContentColor.current.copy(alpha = 1f),
                            )
                        }
                    },
                    hint = stringResource(id = R.string.login_hint_username)
                )
                AnimatedVisibility(visible = loginUIState.value.visibilityUsernameError) { LoginErrorText(text = loginUIState.value.usernameError) }

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.marginTop_login_passwordTextField)))
                LoginPasswordEditText(
                    text = passwordInput.value,
                    onTextChange = { viewModel.onPasswordValueChange(it) },
                    imeAction = ImeAction.Done
                )
                AnimatedVisibility(visible = loginUIState.value.visibilityPasswordError) { LoginErrorText(text = loginUIState.value.passwordError) }

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.marginTop_login_forgotPassTextButton)))
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                    Text(
                        modifier = Modifier
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) { Toast.makeText(context, "Navigated to Forgot password", Toast.LENGTH_SHORT).show() },
                        text = stringResource(id = R.string.login_forgotPassword),
                        fontSize = fontSizeDimensionResource(id = R.dimen.textSize_login_forgotPasswordTextButton),
                        color = colorResource(id = R.color.login_textButton),
                        textAlign = TextAlign.End
                    )
                }

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.marginTop_login_loginButton)))
                Button(
                    onClick = {
                        viewModel.login()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.height_login_loginButton)),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.cornerRadius_login_loginButton)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.login_loginButton)),
                    enabled = loginUIState.value.enableLoginButton
                ) {
                    Text(
                        text = stringResource(id = R.string.all_login),
                        fontSize = fontSizeDimensionResource(id = R.dimen.textSize_login_loginButton),
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Column {
                Text(
                    text = buildAnnotatedString {
                        append(stringResource(id = R.string.login_dontHaveAccount))
                        append(" ")
                        withStyle(SpanStyle(color = colorResource(id = R.color.login_textButton))) {
                            append(stringResource(id = R.string.all_signup))
                        }
                    },
                    fontSize = fontSizeDimensionResource(id = R.dimen.textSize_login_registerTextButton),
                    modifier = Modifier
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) { Toast.makeText(context, "Navigated to register", Toast.LENGTH_SHORT).show() }
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.marginBottom_login_registerTextButton)))
            }
        }
    }
    AnimatedVisibility(visible = loginUIState.value.isLoading, enter = fadeIn(), exit = fadeOut()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray.copy(0.5f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(dimensionResource(id = R.dimen.size_login_progressBar)),
                color = colorResource(id = R.color.login_progressBar),
                strokeWidth = dimensionResource(id = R.dimen.progressBarStrokeWidth_login)
            )
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
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(
            topEnd = dimensionResource(id = R.dimen.cornerRadius_login_loginTextField),
            bottomStart = dimensionResource(id = R.dimen.cornerRadius_login_loginTextField)
        ),
        value = text,
        onValueChange = { onTextChange(it) },
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
            if (text.isNotEmpty()) {
                trailingIcon()
            }
        },
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
fun LoginPasswordEditText(
    text: String,
    onTextChange: (String) -> Unit,
    imeAction: ImeAction,
) {
    val passwordVisibility = remember { mutableStateOf(false) }

    val icon = if (passwordVisibility.value)
        painterResource(id = R.drawable.ic_hide_password)
    else
        painterResource(id = R.drawable.ic_show_password)

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(
            topEnd = dimensionResource(id = R.dimen.cornerRadius_login_loginTextField),
            bottomStart = dimensionResource(id = R.dimen.cornerRadius_login_loginTextField)
        ),
        value = text,
        onValueChange = { onTextChange(it) },
        label = {
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
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
            if (text.isNotEmpty()) {
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
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Black
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
