package br.pucpr.authserver.users

import br.pucpr.authserver.roles.Role
import br.pucpr.authserver.rolesLevels.RoleLevel
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "tblUsers")
class User(
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

