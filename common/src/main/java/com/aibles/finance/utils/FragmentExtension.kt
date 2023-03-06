package com.aibles.finance.utils

import androidx.fragment.app.Fragment
import com.aibles.finance.utils.hideKeyboard

fun Fragment.hideKeyboard(){
    activity?.hideKeyboard()
}