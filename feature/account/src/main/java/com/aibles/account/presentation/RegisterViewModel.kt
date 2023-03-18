package com.aibles.account.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aibles.account.data.remote.dto.RegisterRequest
import com.aibles.account.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private var registerUseCase: RegisterUseCase) : ViewModel() {

    fun register(){
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO){
                val response = registerUseCase.invoke(RegisterRequest(
                    username = "huyle",
                    email = "huyle@gmail.com",
                    password = "toilahuyle",
                    fullname = "le nhat huy",
                    confirm_password = "toilahuyle"
                ))

                Log.d("check_response","--- $response")
            }
        }

    }
}