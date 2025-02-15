package br.pucpr.authserver.payStubs

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Positive

@Entity
class PayStub(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "pay_month")
    @Min(1)
    @Max(12)
    var payMonth: Int,

    @Column(name = "pay_year")
    @Min(1900)
    var year: Int,

    @Positive
    var salary: Double
)
