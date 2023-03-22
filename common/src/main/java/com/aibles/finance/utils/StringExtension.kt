package com.aibles.finance.utils

import java.util.regex.Pattern

fun String.isValidUsername(): Boolean{
    val usernamePattern = "^[A-Za-z0-9]+\$"
    val pattern = Pattern.compile(usernamePattern)
    return this.isNotEmpty() && pattern.matcher(this).matches()
}

fun String.isValidPassword(): Boolean{
    val passwordPattern = "^(?=.{8,16})(?=.*[a-z])(?=.*[A-Z])(?=.*[?!@#\$%^&*+=._()-]).*\$"
    val pattern = Pattern.compile(passwordPattern)
    return this.isNotEmpty() && pattern.matcher(this).matches()
}