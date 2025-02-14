package br.pucpr.authserver.employee.controller.requests

import br.pucpr.authserver.employee.Employee
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateEmployeeRequest(
    @field:Email
    @field:NotNull
    val email: String?,

    @field:NotBlank
    val password: String,

    @field:NotBlank
    val name: String,
) {
    fun toUser() = Employee(
        email = email!!, password = password, name = name
    )
}
