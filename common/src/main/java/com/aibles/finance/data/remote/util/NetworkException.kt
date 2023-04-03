package com.aibles.finance.data.remote.util

class NoNetworkException(message: String?) : CustomException(message)
class NetworkAuthenticationException(message: String?) : CustomException(message)
class NetworkServerException(message: String?) : CustomException(message)
class NetworkResourceNotFoundException(message: String?) : CustomException(message)
class RequestTimeoutException(message: String?) : CustomException(message)
class BadRequestException(message: String?) : CustomException(message)
class UnknownException(message: String?) : CustomException(message)
class NetworkException(message: String?) : CustomException(message)
sealed class CustomException(message: String?) : Exception(message)