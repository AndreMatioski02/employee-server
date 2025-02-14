package br.pucpr.authserver.rolesLevels

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class RoleLevelService(val roleLevelRepository: RoleLevelRepository) {
    fun save(roleLevel: RoleLevel): RoleLevel = roleLevelRepository.save(roleLevel)

    fun findByNameOrNull(id: Long): RoleLevel? = roleLevelRepository.findByIdOrNull(id)

    fun findAll(): MutableList<RoleLevel> = roleLevelRepository.findAll()
}