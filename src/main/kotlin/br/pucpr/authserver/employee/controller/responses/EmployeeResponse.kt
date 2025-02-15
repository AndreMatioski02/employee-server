package br.pucpr.authserver.employee.controller.responses

import br.pucpr.authserver.roleLevels.RoleLevel
import br.pucpr.authserver.employee.Employee
import br.pucpr.authserver.payStubs.PayStub

data class EmployeeResponse(
    val id: Long,
    val name: String,
    val email: String,
    val roleLevel: RoleLevel?,
    val payStubs: MutableSet<PayStub>
) {
    constructor(employee: Employee): this(id=employee.id!!, employee.name, employee.email, employee.roleLevel, employee.payStubs)
}
