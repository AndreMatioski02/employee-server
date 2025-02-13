package br.pucpr.authserver.roleLevels

import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class RoleLevelService(val roleLevelRepository: RoleLevelRepository) {
    fun save(roleLevel: RoleLevel): RoleLevel = roleLevelRepository.save(roleLevel)

    fun findAll(): MutableList<RoleLevel> = roleLevelRepository.findAll(Sort.by("name"))

    fun findByNameOrNull(name: String): RoleLevel? = roleLevelRepository.findByIdOrNull(name)
}