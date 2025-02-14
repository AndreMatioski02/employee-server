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
    fun save(role: Role): Role = roleRepository.save(role)

    fun findAll(): MutableList<Role> = roleRepository.findAll(Sort.by("name"))

    fun findByNameOrNull(name: String): Role? = roleRepository.findByIdOrNull(name)

    fun addLevel(roleName: String, levelId: Long): Boolean {
        val roleUpper = roleName.uppercase()
        val role = findByNameOrNull(roleName.uppercase()) ?: throw NotFoundException("Role $roleUpper not found")

        val level = levelService.findByIdOrNull(levelId) ?: throw NotFoundException("Level $levelId not found")

        val roleLevel = RoleLevel(role = role, level = level,)

        roleLevelService.save(roleLevel)
        roleRepository.save(role)
        return true
    }
}