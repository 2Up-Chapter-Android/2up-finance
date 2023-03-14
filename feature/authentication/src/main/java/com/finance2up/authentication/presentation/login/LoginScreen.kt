package com.finance2up.authentication.presentation.login

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
import androidx.compose.ui.res.painterResource
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.finance2up.authentication.R

@Composable
fun LoginScreen() {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val temp = remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 28.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { focusManager.clearFocus() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.image_welcome_login),
                contentDescription = "",
                modifier = Modifier
                    .width(200.dp)
                    .height(221.dp)
                    .padding(vertical = 18.dp),
            )

            Text(text = "Welcome to 2upFinance", fontSize = 24.sp)

            Spacer(modifier = Modifier.height(25.dp))
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
                hint = "Username"
            )
            AnimatedVisibility(visible = true){ LoginErrorText(text = "Tài khoản không tồn tại") }
            
            Spacer(modifier = Modifier.height(15.dp))
            LoginPasswordEditText(
                text = "",
                onTextChange = {},
                imeAction = ImeAction.Done,
                hint = "Password"
            )
            AnimatedVisibility(visible = true){ LoginErrorText(text = "Mật khẩu không chính xác") }

            Spacer(modifier = Modifier.height(7.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                Text(
                    modifier = Modifier
                        .clickable(interactionSource = interactionSource, indication = null) { },
                    text = "Forgot Password?",
                    fontSize = 14.sp,
                    color = Color(0xFF007AFF),
                    textAlign = TextAlign.End
                )
            }

            Spacer(modifier = Modifier.height(38.dp))
            Button(
                onClick = { temp.value = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF5B5E))
            ) {
                Text(
                    text = "Đăng nhập",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            
            Spacer(modifier = Modifier.height(40.dp))
            AnimatedVisibility(visible = temp.value){
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp),
                    color = Color(0xFFFF0000),
                    strokeWidth = 5.dp
                )
            }
        }

        Column {
            Text(
                text = buildAnnotatedString {
                    append("Don’t have an account? ")
                    withStyle(SpanStyle(color = Color(0xFF007AFF))) { append("Sign up") }
                },
                modifier = Modifier
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) { }
            )
            Spacer(modifier = Modifier.height(37.dp))
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
            .height(56.dp)
            .border(width = 1.dp, color = Color.Black, RoundedCornerShape(28.dp))
            .padding(end = 15.dp),
        value = textValue.value,
        onValueChange = {
//            onTextChange(it)
            textValue.value = it
        },
        placeholder = {
            Text(
                modifier = Modifier
                    .alpha(ContentAlpha.medium),
                text = hint,
                color = Color.Black,
                fontSize = 13.sp
            )
        },
        textStyle = TextStyle(
            fontSize = 13.sp
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
    hint: String
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
            .height(56.dp)
            .border(width = 1.dp, color = Color.Black, RoundedCornerShape(28.dp))
            .padding(end = 15.dp),
        value = password.value,
        onValueChange = {
//            onTextChange(it)
            password.value = it
        },
        placeholder = {
            Text(
                modifier = Modifier
                    .alpha(ContentAlpha.medium),
                text = hint,
                color = Color.Black,
                fontSize = 13.sp
            )
        },
        textStyle = TextStyle(
            fontSize = 13.sp
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
fun LoginErrorText(text: String){
    Column(modifier = Modifier.fillMaxWidth().padding(start = 10.dp)) {
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = text, fontWeight = FontWeight.Bold, fontSize = 9.sp, color = Color(0xFFFF5B5E))
    }
}
