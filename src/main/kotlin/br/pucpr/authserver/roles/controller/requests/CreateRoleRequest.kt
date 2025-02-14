package br.pucpr.authserver.roles.controller.requests

import br.pucpr.authserver.roles.Role
import jakarta.validation.constraints.NotBlank

data class CreateRoleRequest (
    @field:NotBlank
    val name: String,

    @field:NotBlank
    val description: String,
) {
    fun toRole() = Role(
        name = name, description = description
    )
}