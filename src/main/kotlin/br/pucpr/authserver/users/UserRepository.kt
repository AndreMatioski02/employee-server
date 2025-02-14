package br.pucpr.authserver.users

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository: JpaRepository<User, Long> {
    fun findByEmail(email: String): User?

    @Query(
        "select distinct u from User u " +
                "join u.roleLevel r " +
                "where r.id = :roleLevel",
    )
    fun findByRoleLevel(roleLevel: Long): List<User>
}