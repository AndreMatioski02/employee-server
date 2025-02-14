package br.pucpr.authserver.rolesLevels

import br.pucpr.authserver.roles.Role
import br.pucpr.authserver.levels.Level
import jakarta.persistence.*

@Entity
class RoleLevel(
    @Id @GeneratedValue
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    var role: Role,

    @ManyToOne
    @JoinColumn(name = "level_id", nullable = false)
    var level: Level
)
