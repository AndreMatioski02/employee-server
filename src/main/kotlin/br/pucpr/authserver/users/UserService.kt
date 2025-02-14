package br.pucpr.authserver.users

import br.pucpr.authserver.exception.NotFoundException
import br.pucpr.authserver.rolesLevels.RoleLevelService
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
    val roleLevelService: RoleLevelService,
) {
    fun insert(user: User): User = userRepository.save(user)

    fun findAll(dir: SortDir, roleLevel: Long?): List<User> {
        if(roleLevel != null) {
            return userRepository.findByRoleLevel(roleLevel)
        }
        return when(dir) {
            SortDir.ASC -> userRepository.findAll(Sort.by("name"))
            SortDir.DESC -> userRepository.findAll(Sort.by("name").descending())
        }
    }

    fun findByIdOrNull(id: Long): User? = userRepository.findByIdOrNull(id)

    fun delete(id: Long): Unit = userRepository.deleteById(id)

    fun addRoleLevel(id: Long, roleLevelId: Long): Boolean {
        val user = findByIdOrNull(id) ?: throw NotFoundException("User $id not found")

        val roleLevel = roleLevelService.findByNameOrNull(roleLevelId) ?: throw NotFoundException("Role $roleLevelId not found")

        user.roleLevel = roleLevel
        userRepository.save(user)
        return true
    }
}