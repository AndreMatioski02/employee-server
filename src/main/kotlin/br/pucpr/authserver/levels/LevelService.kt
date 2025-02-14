package br.pucpr.authserver.levels

import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class LevelService(val levelRepository: LevelRepository) {
    fun insert(level: Level): Level = levelRepository.save(level)

    fun findAll(): MutableList<Level> = levelRepository.findAll(Sort.by("name"))

    fun findByIdOrNull(id: Long): Level? = levelRepository.findByIdOrNull(id)
}