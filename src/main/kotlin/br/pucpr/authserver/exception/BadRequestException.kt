package br.pucpr.authserver.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.IllegalArgumentException

@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadRequestException(
    message: String? = "Bad Request",
    cause: Throwable? = null
): IllegalArgumentException(message, cause)