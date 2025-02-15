package br.pucpr.authserver.payStubs

import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PayStubService(val payStubRepository: PayStubRepository) {
    fun insert(payStub: PayStub): PayStub = payStubRepository.save(payStub)

    fun findAll(): MutableList<PayStub> = payStubRepository.findAll()

    fun findByIdOrNull(id: Long): PayStub? = payStubRepository.findByIdOrNull(id)

    fun delete(id: Long): Unit = payStubRepository.deleteById(id)
}