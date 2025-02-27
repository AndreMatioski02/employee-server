package br.pucpr.authserver.employee.controller.requests

import jakarta.validation.constraints.NotBlank

data class LoginRequest (
    @NotBlank
    var email: String?,

    @NotBlank
    var password: String?
)