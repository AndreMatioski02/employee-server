package br.pucpr.authserver.users

import br.pucpr.authserver.roles.Role
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
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

    @ManyToMany
    @JoinTable(
        name="userRoles",
        joinColumns = [JoinColumn(name = "idUser")],
        inverseJoinColumns = [JoinColumn(name="idRole")]
    )
    val roles: MutableSet<Role> = mutableSetOf()
)

