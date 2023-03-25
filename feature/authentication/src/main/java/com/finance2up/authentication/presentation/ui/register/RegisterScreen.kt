package com.finance2up.authentication.presentation.ui.register

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import com.finance2up.authentication.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.finance2up.authentication.presentation.util.fontSizeDimensionResource


@Composable
fun RegisterScreen() {

    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val context = LocalContext.current

    val viewModel: RegisterViewModel = hiltViewModel()
    val userNameInput = viewModel.usernameInput.collectAsStateWithLifecycle()
    val fullNameInput = viewModel.fullNameInput.collectAsStateWithLifecycle()
    val emailAddressInput = viewModel.emailAddressInput.collectAsStateWithLifecycle()
    val passwordInput = viewModel.passwordInput.collectAsStateWithLifecycle()
    val passwordConfirmInput = viewModel.passwordConfirmInput.collectAsStateWithLifecycle()

    val registerState = viewModel.registerState.collectAsStateWithLifecycle()
    val registerUiState = viewModel.registerUiState.collectAsStateWithLifecycle()

//    val uiState = remember { RegisterUiState() }

    if (registerState.value.isSuccessful()) Toast.makeText(
        context,
        "Register Success",
        Toast.LENGTH_SHORT
    ).show()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Canvas(modifier = Modifier.fillMaxSize(),
            onDraw = {
                drawRect(color = Color(0xFFFAADAD))
            }
        )
        Canvas(modifier = Modifier.fillMaxSize(),
            onDraw = {
                drawCircle(
                    color = Color(0xFF6B3D88),
                    center = Offset(
                        90.dp.toPx(),
                        -100.dp.toPx()
                    ),
                    radius = 350.dp.toPx()
                )
            }
        )

        Canvas(modifier = Modifier.fillMaxSize(),
            onDraw = {
                drawCircle(
                    color = Color.White,
                    center = Offset(
                        350.dp.toPx(),
                        830.dp.toPx()
                    ),
                    radius = 200.dp.toPx()
                )
            }
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .verticalScroll(
                    rememberScrollState()
                )
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) { focusManager.clearFocus() },
        ) {

            Column(
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.marginStart_column_register),
                    end = dimensionResource(id = R.dimen.marginEnd_column_register)
                )
            ) {
                Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.marginTop_title_register)))

                Text(
                    text = stringResource(id = R.string.all_signup),
                    color = Color.White,
                    fontSize = fontSizeDimensionResource(id = R.dimen.textSize_register_registerTitle),
                )
                Text(
                    text = stringResource(id = R.string.register_title_two),
                    color = Color.White,
                    fontSize = fontSizeDimensionResource(id = R.dimen.textSize_register_registerTitle),
                )

                Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.marginBottom_title_register)))

                RegisterItem(
                    text = userNameInput.value,
                    onValueChange = { viewModel.onUsernameValueChange(it) },
//                    text = uiState.usernameInput,
//                    onValueChange = {uiState.usernameInput },
                    stringResource(id = R.string.register_hint_username),
                    stringResource(id = R.string.register_name),
                    trailingIcon = {
                        IconButton(onClick = { viewModel.onUsernameValueChange("") }) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = stringResource(id = R.string.register_clear),
                                tint = LocalContentColor.current.copy(alpha = 1f),
                            )
                        }
                    }
                )
                AnimatedVisibility(visible = registerUiState.value.visibilityUsernameError) {
                    RegisterErrorText(
                        text = registerUiState.value.usernameError
                    )
                }

                Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.marginTop_registerItem)))
                RegisterItem(
                    text = fullNameInput.value,
                    onValueChange = { viewModel.onFullNameValueChange(it) },
//                    text = uiState.fullNameInput,
//                    onValueChange = {uiState.fullNameInput},
                    stringResource(id = R.string.register_hint_full_name),
                    stringResource(id = R.string.register_full_name),
                    trailingIcon = {
                        IconButton(onClick = { viewModel.onFullNameValueChange("") }) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = stringResource(id = R.string.register_clear),
                                tint = LocalContentColor.current.copy(alpha = 1f),
                            )
                        }
                    }
                )
                AnimatedVisibility(visible = registerUiState.value.visibilityFullNameError) {
                    RegisterErrorText(
                        text = registerUiState.value.fullNameError
                    )
                }

                Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.marginTop_registerItem)))
                RegisterItem(
                    text = emailAddressInput.value,
                    onValueChange = { viewModel.onEmailAddressValueChange(it) },
//                    text = uiState.emailAddressInput,
//                    onValueChange = {uiState.emailAddressInput},
                    stringResource(id = R.string.register_hint_email),
                    stringResource(id = R.string.register_email),
                    trailingIcon = {
                        IconButton(onClick = { viewModel.onEmailAddressValueChange("") }) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = stringResource(id = R.string.register_clear),
                                tint = LocalContentColor.current.copy(alpha = 1f),
                            )
                        }
                    }
                )
                AnimatedVisibility(visible = registerUiState.value.visibilityEmailAddressError) {
                    RegisterErrorText(
                        text = registerUiState.value.emailAddressError
                    )
                }

                Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.marginTop_registerItem)))
                RegisterPassword(
                    text = passwordInput.value,
                    onValueChange = { viewModel.onPasswordValueChange(it) },
//                    text = uiState.passwordInput,
//                    onValueChange = {uiState.passwordInput},
                    stringResource(id = R.string.register_hint_password),
                    stringResource(id = R.string.register_hint_password),
                    true
                )
                AnimatedVisibility(visible = registerUiState.value.visibilityPasswordError) {
                    RegisterErrorText(
                        text = registerUiState.value.passwordError
                    )
                }

                Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.marginTop_registerItem)))
                RegisterPassword(
                    text = passwordConfirmInput.value,
                    onValueChange = { viewModel.onPasswordConfirmValueChange(it) },
//                    text = uiState.passwordConfirmInput,
//                    onValueChange = {uiState.passwordConfirmInput},
                    stringResource(id = R.string.register_hint_confirm_passWord),
                    stringResource(id = R.string.register_hint_confirm_passWord),
                    false
                )
                AnimatedVisibility(visible = registerUiState.value.visibilityPasswordConfirmError) {
                    RegisterErrorText(
                        text = registerUiState.value.passwordConfirmError
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.all_signup),
                            color = Color.White,
                            fontSize = fontSizeDimensionResource(id = R.dimen.textSize_register_buttonRegister)
                        )

                        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacer_button_register)))

                        Button(
                            onClick = {
                                if (!registerUiState.value.isNullUsername ||
                                    !registerUiState.value.isNullFullNameInput ||
                                    !registerUiState.value.isNullEmailAddress ||
                                    !registerUiState.value.isNullPassword ||
                                    !registerUiState.value.isNullPasswordConfirm
                                ) {
                                    viewModel.register()
                                }
                            },
                            enabled = registerUiState.value.enableLoginButton,
                            shape = RoundedCornerShape(
                                size = dimensionResource(id = R.dimen.RoundedCornerShape_button_register)
                            ),
                            modifier = Modifier.size(dimensionResource(id = R.dimen.size_button_register)),
                            colors = ButtonDefaults.buttonColors(Color(0xFF6B3D88)),
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowForward,
                                contentDescription = stringResource(id = R.string.register_content_description_floating_icon),
                                tint = Color.White,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_CircularProgressIndicator_register)))

                    AnimatedVisibility(
                        visible = registerUiState.value.isLoading,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(80.dp),
                            color = Color.Blue,
                            strokeWidth = dimensionResource(id = R.dimen.progressBarStrokeWidth_register)
                        )
                    }
                    Text(
                        text = buildAnnotatedString {
                            append(stringResource(id = R.string.register_Already_have_an_account))
                            append(stringResource(id = R.string.register_space))
                            withStyle(SpanStyle(color = Color.Blue)) {
                                append(stringResource(id = R.string.all_login))
                            }
                        },
                        fontSize = fontSizeDimensionResource(id = R.dimen.textSize_login_registerTextButton),
                        modifier = Modifier
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) { }
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.margin_CircularProgressIndicator_register)))
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterItem(
    text: String,
    onValueChange: (String) -> Unit,
    textLabel: String,
    textPlaceholder: String,
    trailingIcon: @Composable () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(horizontalAlignment = Alignment.Start) {
        OutlinedTextField(
            value = text, onValueChange = { onValueChange(it) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(
                topEnd = dimensionResource(id = R.dimen.RoundedCornerShape_outLineTextField_register),
                bottomStart = dimensionResource(id = R.dimen.RoundedCornerShape_outLineTextField_register)
            ),
            label = {
                Text(
                    textLabel,
                    color = Color.White,

                    )
            },
            trailingIcon = {
                if (text.isNotEmpty()) {
                    trailingIcon()
                }
            },
            placeholder = {
                Text(text = textPlaceholder)
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.DarkGray,
            ),
            singleLine = true,
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    // i will do something here
                }
            )
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterPassword(
    text: String,
    onValueChange: (String) -> Unit,
    textLabel: String,
    textPlaceholder: String,
    showOrHide: Boolean
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var passwordHidden by rememberSaveable { mutableStateOf(showOrHide) }

    Column(horizontalAlignment = Alignment.Start) {
        OutlinedTextField(
            value = text, onValueChange = { onValueChange(it) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(
                topEnd = dimensionResource(id = R.dimen.RoundedCornerShape_outLineTextField_register),
                bottomStart = dimensionResource(id = R.dimen.RoundedCornerShape_outLineTextField_register)
            ),
            label = {
                Text(
                    textLabel,
                    color = Color.White,
                )
            },
            placeholder = { Text(text = textPlaceholder) },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (passwordHidden) VisualTransformation.None else PasswordVisualTransformation(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.DarkGray,
            ),
            trailingIcon = {
                if (passwordHidden) {
                    IconButton(onClick = { passwordHidden = false }) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = stringResource(R.string.register_hint_password)
                        )
                    }
                } else {
                    IconButton(
                        onClick = { passwordHidden = true }) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = stringResource(R.string.register_hint_password)
                        )
                    }
                }
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    // i will do something here
                }
            )
        )
    }
}

@Composable
fun RegisterErrorText(text: String) {
    Row(
        Modifier
            .height(dimensionResource(id = R.dimen.height_registerErrorText))
            .wrapContentWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text,
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.start_text_registerErrorText)),
            textAlign = TextAlign.Left,
        )
    }
}

@Preview
@Composable
fun Test() {
    RegisterScreen()
}