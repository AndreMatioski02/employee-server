package br.pucpr.authserver.employee

import br.pucpr.authserver.rolesLevels.RoleLevel
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
class Employee(
    @Id @GeneratedValue
    var id: Long? = null,

    @NotBlank
    var name: String,

    @NotBlank
    var password: String,

    @Column(unique = true, nullable = false)
    var email: String,

    @OneToOne
    var roleLevel: RoleLevel? = null
)

