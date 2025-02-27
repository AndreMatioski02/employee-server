package br.pucpr.authserver.roles.controller

import br.pucpr.authserver.levels.controller.requests.CreateLevelRequest
import br.pucpr.authserver.levels.controller.responses.LevelResponse
import br.pucpr.authserver.roles.Role
import br.pucpr.authserver.roles.RoleService
import br.pucpr.authserver.roles.controller.requests.CreateRoleRequest
import br.pucpr.authserver.roles.controller.responses.RoleResponse
import br.pucpr.authserver.security.UserToken
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/roles")
class RoleController(val roleService: RoleService) {
    @SecurityRequirement(name = "AuthServer")
    @PostMapping
    fun insert(@RequestBody @Valid role: CreateRoleRequest, auth: Authentication): ResponseEntity<RoleResponse> {
        val user = auth.principal as UserToken

        return if(user.isRH && user.id.toInt() == 1) {
            roleService.insert(role.toRole())
                .let { RoleResponse(it) }
                .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }
        } else {
            ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }
    }


    @GetMapping
    fun findAll(): List<Role> = roleService.findAll()

    @SecurityRequirement(name = "AuthServer")
    @PostMapping("/{roleId}/levels/{levelId}")
    fun grantRoleAndLevels(
        @PathVariable roleId: Long,
        @PathVariable levelId: Long,
        auth: Authentication): ResponseEntity<Void>
    {
        val user = auth.principal as UserToken

        return if(user.isRH && user.id.toInt() == 1) {
            if (roleService.addLevel(roleId, levelId)) ResponseEntity.ok().build()
            else ResponseEntity.noContent().build()
        } else {
            ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }
    }
}
