package br.pucpr.authserver.users.controller

import br.pucpr.authserver.users.SortDir
import br.pucpr.authserver.users.UserService
import br.pucpr.authserver.users.controller.requests.CreateUserRequest
import br.pucpr.authserver.users.controller.responses.UserResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    val userService: UserService
) {
    @PostMapping
    fun insert(@RequestBody @Valid user: CreateUserRequest) =
        userService.insert(user.toUser())
            .let { UserResponse(it) }
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @GetMapping
    fun findAll(@RequestParam dir: String = "ASC", @RequestParam roleLevel: Long?): ResponseEntity<List<UserResponse>> {
        val sortDir = SortDir.findOrNull(dir) ?: return ResponseEntity.badRequest().build()

        return userService.findAll(sortDir, roleLevel)
            .map { UserResponse(it) }
            .let { ResponseEntity.ok(it) }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) =
        userService.findByIdOrNull(id)
            ?.let { UserResponse(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> =
        userService.delete(id)
            .let { ResponseEntity.ok().build() }

    @PutMapping("/{id}/role-level/{roleLevelId}")
    fun grant(@PathVariable id: Long, @PathVariable roleLevelId: Long): ResponseEntity<Void> =
        if (userService.addRoleLevel(id, roleLevelId)) ResponseEntity.ok().build()
        else ResponseEntity.noContent().build()
}
