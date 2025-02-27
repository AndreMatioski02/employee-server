package br.pucpr.authserver.employee.controller.responses

data class LoginResponse(
    val token: String,
    val employee: EmployeeResponse,
)
