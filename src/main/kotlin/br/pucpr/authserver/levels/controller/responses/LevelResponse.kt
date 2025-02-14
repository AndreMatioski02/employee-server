package br.pucpr.authserver.levels.controller.responses

import br.pucpr.authserver.levels.Level

data class LevelResponse(
    val id: Long,
    val name: String,
    val description: String,
) {
    constructor(level: Level): this(id=level.id!!, level.name, level.description)
}
