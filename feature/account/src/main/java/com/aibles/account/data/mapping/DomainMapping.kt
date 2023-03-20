package com.aibles.account.data.mapping

import com.aibles.account.data.remote.dto.Data
import com.aibles.account.data.remote.dto.RegisterResponse
import com.aibles.account.domain.entity.AccountInformation
import com.aibles.account.domain.entity.RegisterInfo


fun RegisterResponse.mapToDomain(): RegisterInfo {
    return RegisterInfo(
        status = status,
        statusMessage = statusMessage,
        timestamp = timestamp,
        data = data?.mapToDomain() ?: AccountInformation()
    )
}

fun Data.mapToDomain(): AccountInformation {
    return AccountInformation(
        id,
        email,
        username,
        fullName,
        activated
    )
}