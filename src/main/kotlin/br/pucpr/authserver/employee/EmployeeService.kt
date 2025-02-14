package br.pucpr.authserver.employee

import br.pucpr.authserver.exception.NotFoundException
import br.pucpr.authserver.roleLevels.RoleLevelService
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class EmployeeService(
    val employeeRepository: EmployeeRepository,
    val roleLevelService: RoleLevelService,
) {
    fun insert(employee: Employee): Employee = employeeRepository.save(employee)

    fun findAll(dir: SortDir, roleLevel: Long?): List<Employee> {
        if(roleLevel != null) {
            return employeeRepository.findByRoleLevel(roleLevel)
        }
        return when(dir) {
            SortDir.ASC -> employeeRepository.findAll(Sort.by("name"))
            SortDir.DESC -> employeeRepository.findAll(Sort.by("name").descending())
        }
    }

    fun findByIdOrNull(id: Long): Employee? = employeeRepository.findByIdOrNull(id)

    fun delete(id: Long): Unit = employeeRepository.deleteById(id)

    fun addRoleLevel(id: Long, roleLevelId: Long): Boolean {
        val user = findByIdOrNull(id) ?: throw NotFoundException("User $id not found")

        val roleLevel = roleLevelService.findByNameOrNull(roleLevelId) ?: throw NotFoundException("Role $roleLevelId not found")

        user.roleLevel = roleLevel
        employeeRepository.save(user)
        return true
    }
}