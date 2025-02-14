package br.pucpr.authserver.rolesLevels

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/role-level")
class RoleLevelController(val roleLevelService: RoleLevelService) {
    @GetMapping
    fun findAll(): List<RoleLevel> = roleLevelService.findAll()
}