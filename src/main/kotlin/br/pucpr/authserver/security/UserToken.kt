package br.pucpr.authserver.security

import br.pucpr.authserver.employee.Employee
import br.pucpr.authserver.roleLevels.RoleLevel
import com.fasterxml.jackson.annotation.JsonIgnore

data class UserToken(
    val id: Long,
    val name: String,
    val roleLevel: RoleLevel?
) {
    constructor() : this(0, "", null)
    constructor(employee: Employee): this(
        id = employee.id!!,
        name = employee.name,
        roleLevel = employee.roleLevel
    )

    @get:JsonIgnore
    val isAdmin: Boolean get() = roleLevel?.id?.toInt() == 1
}
