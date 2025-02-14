package br.pucpr.authserver.roles.controller

import br.pucpr.authserver.roles.Role
import br.pucpr.authserver.roles.RoleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/roles")
class RoleController(val roleService: RoleService) {
    @PostMapping
    fun insert(@RequestBody role: Role) = roleService.save(role).let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @GetMapping
    fun findAll(): List<Role> = roleService.findAll()

    @PostMapping("/{role}/levels/{levelId}")
    fun grantRoleAndLevels(@PathVariable role: String, @PathVariable levelId: Long): ResponseEntity<Void> =
        if (roleService.addLevel(role, levelId)) ResponseEntity.ok().build()
        else ResponseEntity.noContent().build()
}