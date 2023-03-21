package com.aibles.account.presentation


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AccountScreen(viewModel: RegisterViewModel = hiltViewModel()){
    Row() {
        Text(text = "account", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 30.sp)
        Button(onClick = {
            viewModel.register()
        }) {
            Text(text = "click")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreView(){
    MaterialTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AccountScreen()
        }
    }
}