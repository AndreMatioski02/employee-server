package br.pucpr.authserver.employee

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface EmployeeRepository: JpaRepository<Employee, Long> {
    fun findByEmail(email: String): Employee?

    @Query(
        "select distinct u from Employee u " +
                "join u.roleLevel r " +
                "where r.id = :roleLevel",
    )
    fun findByRoleLevel(roleLevel: Long): List<Employee>
}