package br.pucpr.authserver.levels.controller.requests

import br.pucpr.authserver.levels.Level
import jakarta.validation.constraints.NotBlank

data class CreateLevelRequest (
    @field:NotBlank
    val name: String,

    @field:NotBlank
    val description: String,
) {
    fun toLevel() = Level(
        name = name, description = description
    )
}