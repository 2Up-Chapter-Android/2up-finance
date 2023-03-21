package com.finance2up.authentication.presentation.ui.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import com.finance2up.authentication.R
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
import androidx.compose.ui.unit.sp
import com.finance2up.authentication.presentation.util.Visibility
import com.finance2up.authentication.presentation.util.VisibilityOff
import com.finance2up.authentication.presentation.util.fontSizeDimensionResource

private val canvasDrawCircleTop = Color(0xFF6B3D88)
private val canvasDrawRect = Color(0xFFFAADAD)

var textUserName = ""
var textPhoneNumber = ""
var textEmail = ""
var textPassword = ""
var textConfirmPassword = ""


@Composable
fun RegisterScreen() {
    var clickable by rememberSaveable { mutableStateOf(true) }

    var userName by rememberSaveable { mutableStateOf("") }
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    userName = textUserName
    phoneNumber = textPhoneNumber
    email = textEmail
    password = textPassword
    confirmPassword = textConfirmPassword

    var showErrorName by rememberSaveable { mutableStateOf(false) }
    var showErrorPhone by rememberSaveable { mutableStateOf(false) }
    var showErrorEmail by rememberSaveable { mutableStateOf(false) }
    var showErrorPassword by rememberSaveable { mutableStateOf(false) }
    var showErrorConfirmPassword by rememberSaveable { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    var temp by rememberSaveable { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Canvas(modifier = Modifier.fillMaxSize(),
            onDraw = {
                drawRect(color = canvasDrawRect)
            }
        )
        Canvas(modifier = Modifier.fillMaxSize(),
            onDraw = {
                drawCircle(
                    color = canvasDrawCircleTop,
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

        Box(modifier = Modifier.align(Alignment.TopCenter)) {

            Column{
                Spacer(modifier = Modifier.padding(40.dp))

                Column(modifier = Modifier.padding(start = 50.dp)) {
                    Text(
                        text = stringResource(id = R.string.all_signup),
                        color = Color.White,
                        fontSize = 40.sp,
                    )
                    Text(
                        text = stringResource(id = R.string.register_title_two),
                        color = Color.White,
                        fontSize = 40.sp,
                    )
                }

                Spacer(modifier = Modifier.padding(top = 55.dp))

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .verticalScroll(
                            rememberScrollState()
                        )
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) { focusManager.clearFocus() },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    RegisterName(showErrorName)

                    Spacer(modifier = Modifier.padding(top = 8.dp))
                    RegisterPhone(showErrorPhone)

                    Spacer(modifier = Modifier.padding(top = 8.dp))
                    RegisterEmail(showErrorEmail)

                    Spacer(modifier = Modifier.padding(top = 8.dp))
                    RegisterPassword(showErrorPassword)

                    Spacer(modifier = Modifier.padding(top = 8.dp))
                    RegisterConfirmPassword(showErrorConfirmPassword)

                    Spacer(modifier = Modifier.padding(top = 20.dp))

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
                                fontSize = 32.sp
                            )

                            Spacer(modifier = Modifier.padding(60.dp))
//
                            Button(
                                onClick = {
                                    temp = true

                                    showErrorName = !isValidName(userName)

                                    showErrorPhone = !isValidPhone(phoneNumber)

                                    showErrorEmail = !(isValidEmail(email))

                                    if (password != confirmPassword) {
                                        showErrorConfirmPassword = true
                                        showErrorPassword = true
                                    } else {
                                        showErrorPassword = !(isValidPassword(password))

                                        showErrorConfirmPassword =
                                            !(isValidPassword(confirmPassword))
                                    }
                                    clickable = false
                                },
                                enabled = clickable,
                                shape = RoundedCornerShape(
                                    topEnd = 30.dp,
                                    topStart = 30.dp,
                                    bottomStart = 30.dp,
                                    bottomEnd = 30.dp
                                ),
                                modifier = Modifier.size(60.dp),
                                colors = ButtonDefaults.buttonColors(canvasDrawCircleTop),
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowForward,
                                    contentDescription = stringResource(id = R.string.register_content_description_floating_icon),
                                    tint = Color.White,
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(30.dp))

                        AnimatedVisibility(visible = temp) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(80.dp),
                                color = Color.Blue,
                                strokeWidth = 8.dp
                            )
                        }
                        Text(
                            text = buildAnnotatedString {
                                append(stringResource(id = R.string.register_Already_have_an_account))
                                append(stringResource(id = R.string.spaceeee))
                                withStyle(SpanStyle(color = Color.Blue)) {
                                    append(stringResource(id = R.string.all_login))
                                }
                            },
                            fontSize = fontSizeDimensionResource(id = R.dimen.textSize_register_login),
                            modifier = Modifier
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null
                                ) { }
                        )
                    }
                }

                Spacer(modifier = Modifier.padding(top = 30.dp))
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterName(showErrorName: Boolean) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var name by rememberSaveable { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.Start) {
        OutlinedTextField(
            value = name, onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = RoundedCornerShape(topEnd = 16.dp, bottomStart = 16.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.register_hint_username),
                    color = Color.White,
                )
            },
            trailingIcon = {
                if (name.isNotEmpty()) {
                    IconButton(onClick = { name = "" }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = stringResource(id = R.string.clear),
                            tint = LocalContentColor.current.copy(alpha = 1f),
                        )
                    }
                }
            },
            placeholder = { Text(text = stringResource(id = R.string.register_name)) },
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
        if (showErrorName) {
            Row(
                Modifier
                    .height(20.dp)
                    .wrapContentWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    stringResource(id = R.string.register_error_incorrect_userName),
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 4.dp),
                    textAlign = TextAlign.Left,
                    maxLines = 3
                )
            }
        }
    }
    textUserName = name
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterPhone(showErrorPhone: Boolean) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var phoneNumber by rememberSaveable { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.Start) {
        OutlinedTextField(
            value = phoneNumber, onValueChange = { phoneNumber = it },
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = RoundedCornerShape(topEnd = 16.dp, bottomStart = 16.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.register_hint_phone),
                    color = Color.White,
                )
            },
            placeholder = { Text(text = stringResource(id = R.string.register_phone)) },
            trailingIcon = {
                if (phoneNumber.isNotEmpty()) {
                    IconButton(onClick = { phoneNumber = "" }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = stringResource(id = R.string.spaceeee),
                            tint = LocalContentColor.current.copy(alpha = 1f),
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Phone
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
        if (showErrorPhone) {
            Row(
                Modifier
                    .height(20.dp)
                    .wrapContentWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    stringResource(id = R.string.register_error_incorrect_phone_number),
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 4.dp),
                    textAlign = TextAlign.Left,
                )
            }
        }
    }
    textPhoneNumber = phoneNumber
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterEmail(showErrorEmail: Boolean) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var email by rememberSaveable { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.Start) {
        OutlinedTextField(
            value = email, onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = RoundedCornerShape(topEnd = 16.dp, bottomStart = 16.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.register_hint_email),
                    color = Color.White,
                )
            },
            placeholder = { Text(text = stringResource(id = R.string.register_email)) },
            trailingIcon = {
                if (email.isNotEmpty()) {
                    IconButton(onClick = { email = "" }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = stringResource(id = R.string.spaceeee),
                            tint = LocalContentColor.current.copy(alpha = 1f),
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.DarkGray,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    // i will do something here
                }
            )
        )
        if (showErrorEmail) {
            Row(
                Modifier
                    .height(20.dp)
                    .wrapContentWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    stringResource(id = R.string.register_error_incorrect_email),
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 4.dp),
                    textAlign = TextAlign.Left,
                )
            }
        }
    }

    textEmail = email
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterPassword(showErrorPassword: Boolean) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    var password by rememberSaveable { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.Start) {
        OutlinedTextField(
            value = password, onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = RoundedCornerShape(topEnd = 16.dp, bottomStart = 16.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.register_hint_password),
                    color = Color.White,
                )
            },
            placeholder = { Text(text = stringResource(id = R.string.register_hint_password)) },
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
                IconButton(onClick = { passwordHidden = !passwordHidden }) {
                    val visibilityIcon = if (passwordHidden) Visibility else VisibilityOff
                    val description =
                        if (passwordHidden) stringResource(id = R.string.show_password) else stringResource(
                            id = R.string.hide_password
                        )
                    Icon(imageVector = visibilityIcon, contentDescription = description)
                }
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    // i will do something here
                }
            )
        )

        if (showErrorPassword) {
            Row(
                Modifier
                    .height(20.dp)
                    .wrapContentWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    stringResource(id = R.string.register_error_incorrect_password),
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 4.dp),
                    textAlign = TextAlign.Left,
                )
            }
        }
    }
    textPassword = password
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterConfirmPassword(showErrorConfirmPassword: Boolean) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var passwordHidden by rememberSaveable { mutableStateOf(false) }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.Start) {
        OutlinedTextField(
            value = confirmPassword, onValueChange = { confirmPassword = it },
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = RoundedCornerShape(topEnd = 16.dp, bottomStart = 16.dp),
            label = {
                Text(
                    text = stringResource(id = R.string.register_hint_confirm_passWord),
                    color = Color.White,
                )
            },
            placeholder = { Text(text = stringResource(id = R.string.register_hint_confirm_passWord)) },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Password
            ),
            visualTransformation =
            if (passwordHidden) VisualTransformation.None else PasswordVisualTransformation(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.DarkGray,
            ),
            trailingIcon = {
                IconButton(onClick = { passwordHidden = !passwordHidden }) {
                    val visibilityIcon = if (passwordHidden) Visibility else VisibilityOff
                    val description =
                        if (passwordHidden) stringResource(id = R.string.show_password) else stringResource(
                            id = R.string.hide_password
                        )
                    Icon(imageVector = visibilityIcon, contentDescription = description)
                }
            },
            singleLine = true,
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    // i will do something here
                }
            )
        )
        if (showErrorConfirmPassword) {
            Row(
                Modifier
                    .height(20.dp)
                    .wrapContentWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    stringResource(id = R.string.register_error_incorrect_confirm_password),
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 4.dp),
                    textAlign = TextAlign.Left,
                )
            }
        }
    }
    textConfirmPassword = confirmPassword
}

fun isValidName(name: String): Boolean {
    return name.matches("[A-Za-z0-9]+".toRegex())
}

fun isValidPhone(phone: String): Boolean {
    return phone.replace("\\D+".toRegex(), "").length >= 10 && phone.matches("[0-9]+".toRegex())
}

fun isValidPassword(password: String): Boolean {
    val regex =
        Regex("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#\$%^&*(),.?\":{}|<>]{8,}\$")
    return regex.matches(password)
}

fun isValidEmail(email: String): Boolean {
    val pattern = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
    return email.matches(pattern)
}

@Preview
@Composable
fun Test() {
    RegisterScreen()
}
