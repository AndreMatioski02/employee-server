package br.pucpr.authserver.roles

import jakarta.persistence.*

@Entity
class Role (
    @Id @GeneratedValue
    val id: Long? = null,
    val name: String,
    val description: String,
)