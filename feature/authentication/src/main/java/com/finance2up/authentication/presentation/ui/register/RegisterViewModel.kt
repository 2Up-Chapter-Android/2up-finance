package com.finance2up.authentication.presentation.ui.register

import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {

    fun registerUser(
        userName: String,
        phoneNumber: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        // Perform the required logic on the input data
        val isValidName = isValidName(userName)
        val isValidPhone = isValidPhone(phoneNumber)
        val isValidEmail = isValidEmail(email)
        val isValidPassword = isValidPassword(password)

        val showErrorName = !isValidName
        val showErrorPhone = !isValidPhone
        val showErrorEmail = !isValidEmail
        val showErrorPassword = !isValidPassword
        val showErrorConfirmPassword =
            if (password != confirmPassword) true else !(isValidPassword)

        // Pass the processed data to the repository or other required components
        // ...
    }
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
//    val pattern = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
    return email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}