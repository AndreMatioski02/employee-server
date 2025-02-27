package br.pucpr.authserver.employee.controller

import br.pucpr.authserver.employee.SortDir
import br.pucpr.authserver.employee.EmployeeService
import br.pucpr.authserver.employee.controller.requests.CreateEmployeeRequest
import br.pucpr.authserver.employee.controller.requests.LoginRequest
import br.pucpr.authserver.employee.controller.responses.EmployeeResponse
import br.pucpr.authserver.security.UserToken
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/employee")
class EmployeeController(
    val employeeService: EmployeeService
) {
    @PostMapping
    fun insert(@RequestBody @Valid employee: CreateEmployeeRequest) =
        employeeService.insert(employee.toEmployee())
            .let { EmployeeResponse(it) }
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @GetMapping
    fun findAll(@RequestParam dir: String = "ASC", @RequestParam roleLevel: Long?): ResponseEntity<List<EmployeeResponse>> {
        val sortDir = SortDir.findOrNull(dir) ?: return ResponseEntity.badRequest().build()

        return employeeService.findAll(sortDir, roleLevel)
            .map { EmployeeResponse(it) }
            .let { ResponseEntity.ok(it) }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) =
        employeeService.findByIdOrNull(id)
            ?.let { EmployeeResponse(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "AuthServer")
    fun delete(@PathVariable id: String, auth: Authentication): ResponseEntity<Void> {
        val user = auth.principal as UserToken

        return if(user.isRH && user.id.toInt() == 1) {
            employeeService.delete(id.toLong())
                .let { ResponseEntity.ok().build() }
        } else {
            ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }
    }

    @SecurityRequirement(name = "AuthServer")
    @PutMapping("/{id}/role-level/{roleLevelId}")
    fun grantRoleLevel(@PathVariable id: Long, @PathVariable roleLevelId: Long, auth: Authentication): ResponseEntity<Void> {
        val user = auth.principal as UserToken

        return if(user.isRH && user.id.toInt() == 1) {
            employeeService.addRoleLevel(id, roleLevelId)
                .let { ResponseEntity.ok().build() }
        } else {
            ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }
    }

    @SecurityRequirement(name = "AuthServer")
    @PutMapping("/{id}/pay-stub/{payStubId}")
    fun grantPayStub(@PathVariable id: Long, @PathVariable payStubId: Long, auth: Authentication): ResponseEntity<Void> {
        val user = auth.principal as UserToken

        return if(user.isRH && user.id.toInt() == 1) {
            employeeService.addPayStub(id, payStubId)
                .let { ResponseEntity.ok().build() }
        } else {
            ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody employee: LoginRequest) = employeeService.login(employee.email!!, employee.password!!)
        ?.let { ResponseEntity.ok().body(it) }
        ?: ResponseEntity.notFound().build()
}
