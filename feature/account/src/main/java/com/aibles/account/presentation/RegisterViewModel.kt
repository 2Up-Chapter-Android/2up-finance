package com.aibles.account.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aibles.account.domain.entity.RegisterRequest
import com.aibles.account.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private var registerUseCase: RegisterUseCase) : ViewModel() {

    fun register(){
        viewModelScope.launch(Dispatchers.Main) {
            val response = registerUseCase.invoke(
                RegisterRequest(
                    username = "huyle",
                    email = "huyle@gmail.com",
                    password = "toilahuyle",
                    fullName = "le nhat huy",
                    confirmPassword = "toilahuyle"
                )
            )

            Log.d("check_response","--- $response")
        }

    }
}