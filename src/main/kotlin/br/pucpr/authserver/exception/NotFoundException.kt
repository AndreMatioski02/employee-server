package br.pucpr.authserver.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.IllegalArgumentException

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException(
    message: String? = "Not Found",
    cause: Throwable? = null
): IllegalArgumentException(message, cause)