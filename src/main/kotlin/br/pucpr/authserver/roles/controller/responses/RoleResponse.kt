package br.pucpr.authserver.roles.controller.responses

import br.pucpr.authserver.levels.Level
import br.pucpr.authserver.roles.Role

data class RoleResponse(
    val id: Long,
    val name: String,
    val description: String,
) {
    constructor(role: Role): this(id=role.id!!, role.name, role.description)
}
