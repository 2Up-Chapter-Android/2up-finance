package com.finance2up.authentication.presentation.util

fun String.isValidEmail(): Boolean{
    return this.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}
