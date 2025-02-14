package br.pucpr.authserver.roles.controller

import br.pucpr.authserver.levels.controller.requests.CreateLevelRequest
import br.pucpr.authserver.levels.controller.responses.LevelResponse
import br.pucpr.authserver.roles.Role
import br.pucpr.authserver.roles.RoleService
import br.pucpr.authserver.roles.controller.requests.CreateRoleRequest
import br.pucpr.authserver.roles.controller.responses.RoleResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/roles")
class RoleController(val roleService: RoleService) {
    @PostMapping
    fun insert(@RequestBody @Valid role: CreateRoleRequest) =
        roleService.insert(role.toRole())
            .let { RoleResponse(it) }
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @GetMapping
    fun findAll(): List<Role> = roleService.findAll()

    @PostMapping("/{roleId}/levels/{levelId}")
    fun grantRoleAndLevels(@PathVariable roleId: Long, @PathVariable levelId: Long): ResponseEntity<Void> =
        if (roleService.addLevel(roleId, levelId)) ResponseEntity.ok().build()
        else ResponseEntity.noContent().build()
}