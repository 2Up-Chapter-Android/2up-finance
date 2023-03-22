package com.finance2up.authentication.domain.usecase

import com.finance2up.authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: AuthenticationRepository) {
    suspend operator fun invoke(username: String, password: String) = repository.login(username, password)
}