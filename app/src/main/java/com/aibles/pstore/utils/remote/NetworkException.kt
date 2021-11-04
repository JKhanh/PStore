package com.aibles.pstore.utils.remote

class NoNetworkException(message: String?): Exception(message)
class NetworkAuthenticationException(message: String?) : Exception(message)
class NetworkServerException(message: String?) : Exception(message)
class NetworkResourceNotFoundException(message: String?) : Exception(message)
class RequestTimeoutException(message: String?) : Exception(message)
class BadRequestException(message: String?) : Exception(message)
class UnknownException(message: String?) : Exception(message)
class NetworkException(message: String? = null) : Exception(message)