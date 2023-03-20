package com.aibles.account.domain.usecase

import com.aibles.account.domain.entity.RegisterRequest
import com.aibles.account.domain.repository.RegisterRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val registerRepo: RegisterRepository) {
    suspend operator fun invoke(registerRequest: RegisterRequest) = registerRepo.register(registerRequest)
}