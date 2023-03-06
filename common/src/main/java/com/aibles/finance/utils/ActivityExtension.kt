package com.aibles.finance.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val inputMethodManager =  getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    currentFocus?.run {
        inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
        clearFocus()
    }
}
