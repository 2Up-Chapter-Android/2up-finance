package com.aibles.account.data.mapping

import com.aibles.account.data.remote.dto.RegisterResponse
import com.aibles.account.domain.entity.RegisterInfo


fun RegisterResponse?.mapToDomain(): RegisterInfo {
    return this?.let {
        RegisterInfo(
            status = status,
            statusMessage = statusMessage,
            timestamp = timestamp,
            data = data.mapToDomain()
        )
    } ?: RegisterInfo()
}

fun RegisterResponse.Data?.mapToDomain(): RegisterInfo.AccountInformation {
    return this?.let {
        RegisterInfo.AccountInformation(
            id,
            email,
            username,
            fullName,
            activated
        )
    } ?: RegisterInfo.AccountInformation()
}