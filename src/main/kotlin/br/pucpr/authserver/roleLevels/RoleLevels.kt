package br.pucpr.authserver.roleLevels

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class RoleLevels (
    @Id
    val roleLevelId: Long,
    val name: String,
)