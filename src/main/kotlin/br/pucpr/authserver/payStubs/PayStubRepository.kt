package br.pucpr.authserver.payStubs

import org.springframework.data.jpa.repository.JpaRepository

interface PayStubRepository: JpaRepository<PayStub, Long>
