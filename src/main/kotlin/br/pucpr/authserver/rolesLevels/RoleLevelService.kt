package br.pucpr.authserver.rolesLevels

import br.pucpr.authserver.roles.Role
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class RoleLevelService(val roleLevelRepository: RoleLevelRepository) {
    fun save(roleLevel: RoleLevel): RoleLevel = roleLevelRepository.save(roleLevel)

    fun findAll(): MutableList<RoleLevel> = roleLevelRepository.findAll()
}