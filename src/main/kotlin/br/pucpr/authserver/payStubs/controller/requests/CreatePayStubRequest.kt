package br.pucpr.authserver.payStubs.controller.requests

import br.pucpr.authserver.payStubs.PayStub
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Positive

data class CreatePayStubRequest (
    @field:Min(1)
    @field:Max(12)
    val payMonth: Int,

    @field:Min(1900)
    val year: Int,

    @field:Positive
    val salary: Double,
) {
    fun toPayStub() = PayStub(
        payMonth = payMonth,
        year = year,
        salary = salary
    )
}
