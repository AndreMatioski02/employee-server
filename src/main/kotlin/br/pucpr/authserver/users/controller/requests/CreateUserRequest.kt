package br.pucpr.authserver.users.controller.requests

import br.pucpr.authserver.rolesLevels.RoleLevel
import br.pucpr.authserver.users.User
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateUserRequest(
    @field:Email
    @field:NotNull
    val email: String?,

    @field:NotBlank
    val password: String,

    @field:NotBlank
    val name: String,
) {
    fun toUser() = User(
        email = email!!, password = password, name = name
    )
}
