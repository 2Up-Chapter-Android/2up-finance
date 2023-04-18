package com.aibles.finance.data.remote.util

import com.aibles.finance.domain.entity.BaseErrorEntity

class NoNetworkException(errorData: BaseErrorEntity.Data) : CustomException(errorData)
class NetworkAuthenticationException(errorData: BaseErrorEntity.Data) : CustomException(errorData)
class NetworkServerException(errorData: BaseErrorEntity.Data) : CustomException(errorData)
class NetworkResourceNotFoundException(errorData: BaseErrorEntity.Data) : CustomException(errorData)
class RequestTimeoutException(errorData: BaseErrorEntity.Data) : CustomException(errorData)
class BadRequestException(errorData: BaseErrorEntity.Data) : CustomException(errorData)
class UnknownException(errorData: BaseErrorEntity.Data) : CustomException(errorData)
class NetworkException(errorData: BaseErrorEntity.Data) : CustomException(errorData)
sealed class CustomException(val errorData: BaseErrorEntity.Data) : Exception()