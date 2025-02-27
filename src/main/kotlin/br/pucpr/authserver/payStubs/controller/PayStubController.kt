package br.pucpr.authserver.payStubs.controller

import br.pucpr.authserver.employee.controller.responses.EmployeeResponse
import br.pucpr.authserver.levels.controller.responses.LevelResponse
import br.pucpr.authserver.payStubs.PayStub
import br.pucpr.authserver.payStubs.PayStubService
import br.pucpr.authserver.payStubs.controller.requests.CreatePayStubRequest
import br.pucpr.authserver.payStubs.controller.responses.PayStubResponse
import br.pucpr.authserver.security.UserToken
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pay-stubs")
class PayStubController(val payStubService: PayStubService) {
    @SecurityRequirement(name = "AuthServer")
    @PostMapping
    fun insert(
        @RequestBody @Valid payStub: CreatePayStubRequest,
        auth: Authentication
    ): ResponseEntity<PayStubResponse>
    {
        val user = auth.principal as UserToken

        return if(user.isRH && user.id.toInt() == 1) {
            payStubService.insert(payStub.toPayStub())
                .let { PayStubResponse(it) }
                .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }
        } else {
            ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }
    }


    @GetMapping
    fun findAll(): List<PayStub> = payStubService.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) =
        payStubService.findByIdOrNull(id)
            ?.let { PayStubResponse(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @SecurityRequirement(name = "AuthServer")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long, auth: Authentication): ResponseEntity<LevelResponse>
    {
        val user = auth.principal as UserToken

        return if(user.isRH && user.id.toInt() == 1) {
            payStubService.delete(id)
                .let { ResponseEntity.ok().build() }
        } else {
            ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }
    }
}