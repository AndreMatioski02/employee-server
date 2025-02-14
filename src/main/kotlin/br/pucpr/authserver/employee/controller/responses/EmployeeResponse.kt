package br.pucpr.authserver.employee.controller.responses

import br.pucpr.authserver.roleLevels.RoleLevel
import br.pucpr.authserver.employee.Employee

data class EmployeeResponse(
    val id: Long,
    val name: String,
    val email: String,
    val roleLevel: RoleLevel?,
) {
    constructor(employee: Employee): this(id=employee.id!!, employee.name, employee.email, employee.roleLevel)
}
