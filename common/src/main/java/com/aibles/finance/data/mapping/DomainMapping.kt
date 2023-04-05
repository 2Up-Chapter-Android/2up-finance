package com.aibles.finance.data.mapping

import com.aibles.finance.data.remote.util.BaseErrorResponse
import com.aibles.finance.domain.entity.BaseErrorEntity

fun BaseErrorResponse?.mapToDomain(): BaseErrorEntity = this?.let {
    BaseErrorEntity(
        status = status,
        statusMessage = statusMessage,
        timestamp = timestamp,
        data = data.mapToDomain()
    )
}  ?: BaseErrorEntity()

private fun BaseErrorResponse.Data?.mapToDomain(): BaseErrorEntity.Data = this?.let {
    BaseErrorEntity.Data(
        code = code,
        detail = detail
    )
} ?: BaseErrorEntity.Data()