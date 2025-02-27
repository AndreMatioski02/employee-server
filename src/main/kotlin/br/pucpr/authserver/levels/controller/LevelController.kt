package br.pucpr.authserver.levels.controller

import br.pucpr.authserver.levels.Level
import br.pucpr.authserver.levels.LevelService
import br.pucpr.authserver.levels.controller.requests.CreateLevelRequest
import br.pucpr.authserver.levels.controller.responses.LevelResponse
import br.pucpr.authserver.security.UserToken
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/levels")
class LevelController(val levelService: LevelService) {
    @SecurityRequirement(name = "AuthServer")
    @PostMapping
    fun insert(
        @RequestBody
        @Valid level: CreateLevelRequest,
        auth: Authentication
    ): ResponseEntity<LevelResponse>
    {
        val user = auth.principal as UserToken

        return if(user.isRH && user.id.toInt() == 1) {
            levelService.insert(level.toLevel())
                .let { LevelResponse(it) }
                .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }
        } else {
            ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }
    }


    @GetMapping
    fun findAll(): List<Level> = levelService.findAll()
}