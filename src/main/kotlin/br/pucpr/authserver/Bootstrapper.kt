package br.pucpr.authserver

import br.pucpr.authserver.employee.Employee
import br.pucpr.authserver.levels.Level
import br.pucpr.authserver.levels.LevelRepository
import br.pucpr.authserver.roles.Role
import br.pucpr.authserver.roles.RoleRepository
import br.pucpr.authserver.roleLevels.RoleLevel
import br.pucpr.authserver.roleLevels.RoleLevelRepository
import br.pucpr.authserver.employee.EmployeeRepository
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class Bootstrapper(
    private val rolesRepository: RoleRepository,
    private val levelRepository: LevelRepository,
    private val roleLevelRepository: RoleLevelRepository,
    private val employeeRepository: EmployeeRepository,
): ApplicationListener<ContextRefreshedEvent> {
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        val role = Role(
            name = "Desenvolvedor",
            description = "Front-end"
        )

        val level = Level(
            name = "Sênior",
            description = "Large Experience"
        )

        val roleLevel = RoleLevel(
            role = role,
            level = level
        )

        if(rolesRepository.findByIdOrNull(1) == null) {
            rolesRepository.save(role)
        }

        if(levelRepository.findByIdOrNull(1) == null) {
            levelRepository.save(level)
        }

        if(roleLevelRepository.findByIdOrNull(1) == null) {
            roleLevelRepository.save(roleLevel)
        }

        if(employeeRepository.findByRoleLevel(1).isEmpty()) {
            val admin = Employee(
                email="andre@email.com",
                password = "admin",
                name = "André Matioski",
                roleLevel = roleLevel
            )

            employeeRepository.save(admin)
        }
    }
}