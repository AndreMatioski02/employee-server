package br.pucpr.authserver.payStubs.controller

import br.pucpr.authserver.employee.controller.responses.EmployeeResponse
import br.pucpr.authserver.payStubs.PayStub
import br.pucpr.authserver.payStubs.PayStubService
import br.pucpr.authserver.payStubs.controller.requests.CreatePayStubRequest
import br.pucpr.authserver.payStubs.controller.responses.PayStubResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pay-stubs")
class PayStubController(val payStubService: PayStubService) {
    @PostMapping
    fun insert(@RequestBody @Valid payStub: CreatePayStubRequest) =
        payStubService.insert(payStub.toPayStub())
            .let { PayStubResponse(it) }
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @GetMapping
    fun findAll(): List<PayStub> = payStubService.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) =
        payStubService.findByIdOrNull(id)
            ?.let { PayStubResponse(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> =
        payStubService.delete(id)
            .let { ResponseEntity.ok().build() }

//    @PutMapping("/{id}/role-level/{roleLevelId}")
//    fun grant(@PathVariable id: Long, @PathVariable roleLevelId: Long): ResponseEntity<Void> =
//        if (payStubService.addRoleLevel(id, roleLevelId)) ResponseEntity.ok().build()
//        else ResponseEntity.noContent().build()
}