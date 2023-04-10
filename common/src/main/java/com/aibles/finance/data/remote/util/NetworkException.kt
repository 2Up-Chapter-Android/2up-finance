package com.aibles.finance.data.remote.util

import com.aibles.finance.domain.entity.BaseErrorEntity

class NoNetworkException(errorData: BaseErrorEntity.Data?, message: String = "") : CustomException(errorData, message)
class NetworkAuthenticationException(errorData: BaseErrorEntity.Data?) : CustomException(errorData)
class NetworkServerException(errorData: BaseErrorEntity.Data?) : CustomException(errorData)
class NetworkResourceNotFoundException(errorData: BaseErrorEntity.Data?) : CustomException(errorData)
class RequestTimeoutException(errorData: BaseErrorEntity.Data?) : CustomException(errorData)
class BadRequestException(errorData: BaseErrorEntity.Data?) : CustomException(errorData)
class UnknownException(errorData: BaseErrorEntity.Data?, message: String = "") : CustomException(errorData, message)
class NetworkException(errorData: BaseErrorEntity.Data?) : CustomException(errorData)
sealed class CustomException(val errorData: BaseErrorEntity.Data?, val msg: String = "") : Exception()