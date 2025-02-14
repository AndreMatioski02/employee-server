package br.pucpr.authserver.levels

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/levels")
class LevelController(val levelService: LevelService) {
    @PostMapping
    fun insert(@RequestBody level: Level) = levelService.save(level).let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @GetMapping
    fun findAll(): List<Level> = levelService.findAll()
}