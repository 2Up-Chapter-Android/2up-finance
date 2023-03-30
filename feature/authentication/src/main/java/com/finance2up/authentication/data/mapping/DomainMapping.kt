package com.finance2up.authentication.data.mapping

import com.finance2up.authentication.data.remote.dto.login.LoginResponse
import com.finance2up.authentication.data.remote.dto.register.RegisterResponse
import com.finance2up.authentication.domain.entity.login.LoginResponseEntity
import com.finance2up.authentication.domain.entity.register.RegisterInfo

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
            id = id,
            email = email,
            username = username,
            fullName = fullName,
            activated = activated
        )
    } ?: RegisterInfo.AccountInformation()
}

fun LoginResponse?.mapToDomain(): LoginResponseEntity {
    return this?.let {
        LoginResponseEntity(
            data = data.mapToDomain(),
            message = message,
            status = status
        )
    } ?: LoginResponseEntity()
}

private fun LoginResponse.LoginResponseData?.mapToDomain(): LoginResponseEntity.LoginResponseData {
    return this?.let {
        LoginResponseEntity.LoginResponseData(
            accessToken = accessToken,
            accessTokenLifeTime = accessTokenLifeTime,
            refreshToken = refreshToken,
            refreshTokenLifeTime = refreshTokenLifeTime,
            tokenType = tokenType
        )
    } ?: LoginResponseEntity.LoginResponseData()
}
