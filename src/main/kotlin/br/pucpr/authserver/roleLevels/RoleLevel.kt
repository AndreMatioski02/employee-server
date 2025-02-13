package br.pucpr.authserver.roleLevels

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class RoleLevel (
    @Id @GeneratedValue
    var roleLevelId: Long? = null,
    val name: String,
)