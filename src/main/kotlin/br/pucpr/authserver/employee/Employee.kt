package br.pucpr.authserver.employee

import br.pucpr.authserver.payStubs.PayStub
import br.pucpr.authserver.roleLevels.RoleLevel
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

    @ManyToOne
    var roleLevel: RoleLevel? = null,

    @OneToMany
    val payStubs: MutableSet<PayStub> = mutableSetOf()
) {
    constructor() : this(null, "", "", "", null)
}

