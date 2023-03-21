package com.finance2up.authentication.domain.usecase

import com.finance2up.authentication.domain.entity.register.RegisterRequest
import com.finance2up.authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repository: AuthenticationRepository) {
    suspend operator fun invoke(registerRequest: RegisterRequest) = repository.register(registerRequest)
}