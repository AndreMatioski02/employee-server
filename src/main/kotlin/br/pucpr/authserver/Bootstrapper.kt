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
        val rhRole = Role(
            name = "RH",
            description = "Recurso humano"
        )

        val devRole = Role(
            name = "Desenvolvedor",
            description = "Front-end dev"
        )

        val level = Level(
            name = "Sênior",
            description = "Large Experience"
        )

        val rhRoleLevel = RoleLevel(
            role = rhRole,
            level = level
        )

        val devRoleLevel = RoleLevel(
            role = devRole,
            level = level
        )

        if(rolesRepository.findByIdOrNull(1) == null) {
            rolesRepository.save(rhRole)
        }

        if(rolesRepository.findByIdOrNull(2) == null) {
            rolesRepository.save(devRole)
        }

        if(levelRepository.findByIdOrNull(1) == null) {
            levelRepository.save(level)
        }

        if(roleLevelRepository.findByIdOrNull(1) == null) {
            roleLevelRepository.save(rhRoleLevel)
        }

        if(roleLevelRepository.findByIdOrNull(2) == null) {
            roleLevelRepository.save(devRoleLevel)
        }

        if(employeeRepository.findByRoleLevel(1).isEmpty()) {
            val rh = Employee(
                email="helena@email.com",
                password = "rh",
                name = "Helena Matioski",
                roleLevel = rhRoleLevel
            )

            employeeRepository.save(rh)
        }

        if(employeeRepository.findByRoleLevel(2).isEmpty()) {
            val dev = Employee(
                email="andre@email.com",
                password = "dev",
                name = "André Matioski",
                roleLevel = devRoleLevel
            )

            employeeRepository.save(dev)
        }

    }
}