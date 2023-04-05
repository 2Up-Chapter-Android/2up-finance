package com.aibles.finance.data.remote.util

class NoNetworkException(error: Error?) : CustomException(error)
class NetworkAuthenticationException(error: Error?) : CustomException(error)
class NetworkServerException(error: Error?) : CustomException(error)
class NetworkResourceNotFoundException(error: Error?) : CustomException(error)
class RequestTimeoutException(error: Error?) : CustomException(error)
class BadRequestException(error: Error?) : CustomException(error)
class UnknownException(error: Error?) : CustomException(error)
class NetworkException(error: Error?) : CustomException(error)
sealed class CustomException(error:Error?) : Exception()