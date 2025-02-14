package br.pucpr.authserver.levels

import jakarta.persistence.*

@Entity
class Level (
    @Id @GeneratedValue
    var id: Long? = null,
    val name: String,
    val description: String,
)