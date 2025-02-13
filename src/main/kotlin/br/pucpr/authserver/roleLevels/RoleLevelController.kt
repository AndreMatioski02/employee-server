package br.pucpr.authserver.roleLevels

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/role-levels")
class RoleLevelController(val roleLevelService: RoleLevelService) {
    @PostMapping
    fun insert(@RequestBody roleLevel: RoleLevel) = roleLevelService.save(roleLevel).let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @GetMapping
    fun findAll(): List<RoleLevel> = roleLevelService.findAll()
}