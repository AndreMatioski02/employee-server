package br.pucpr.authserver.payStubs.controller.responses

import br.pucpr.authserver.payStubs.PayStub

data class PayStubResponse(
    val id: Long,
    val payMonth: Int,
    val year: Int,
    val salary: Double,
) {
    constructor(payStub: PayStub): this(id=payStub.id!!, payStub.payMonth, payStub.year, payStub.salary)
}
