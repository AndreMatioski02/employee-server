package br.pucpr.authserver.employee.controller

import br.pucpr.authserver.employee.SortDir
import br.pucpr.authserver.employee.EmployeeService
import br.pucpr.authserver.employee.controller.requests.CreateEmployeeRequest
import br.pucpr.authserver.employee.controller.responses.EmployeeResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/employee")
class UserController(
    val employeeService: EmployeeService
) {
    @PostMapping
    fun insert(@RequestBody @Valid user: CreateEmployeeRequest) =
        employeeService.insert(user.toUser())
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
    fun delete(@PathVariable id: Long): ResponseEntity<Void> =
        employeeService.delete(id)
            .let { ResponseEntity.ok().build() }

    @PutMapping("/{id}/role-level/{roleLevelId}")
    fun grant(@PathVariable id: Long, @PathVariable roleLevelId: Long): ResponseEntity<Void> =
        if (employeeService.addRoleLevel(id, roleLevelId)) ResponseEntity.ok().build()
        else ResponseEntity.noContent().build()
}
