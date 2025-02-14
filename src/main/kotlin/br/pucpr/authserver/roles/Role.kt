package br.pucpr.authserver.roles

import br.pucpr.authserver.rolesLevels.RoleLevel
import jakarta.persistence.*
import org.intellij.lang.annotations.Pattern

@Entity
class Role (
    @Id
    @Pattern("^[A-Z][A-Z0-9]+$")
    val name: String,
    val description: String,
)