package br.pucpr.authserver.roles

import br.pucpr.authserver.exception.NotFoundException
import br.pucpr.authserver.levels.LevelService
import br.pucpr.authserver.rolesLevels.RoleLevel
import br.pucpr.authserver.rolesLevels.RoleLevelService
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class RoleService(val roleRepository: RoleRepository, val levelService: LevelService, val roleLevelService: RoleLevelService) {
    fun insert(role: Role): Role = roleRepository.save(role)

    fun findAll(): MutableList<Role> = roleRepository.findAll(Sort.by("name"))

    fun findByNameOrNull(id: Long): Role? = roleRepository.findByIdOrNull(id)

    fun addLevel(roleId: Long, levelId: Long): Boolean {
        val role = findByNameOrNull(roleId) ?: throw NotFoundException("Role $roleId not found")

        val level = levelService.findByIdOrNull(levelId) ?: throw NotFoundException("Level $levelId not found")

        val roleLevel = RoleLevel(role = role, level = level)

        roleLevelService.save(roleLevel)
        roleRepository.save(role)
        return true
    }
}