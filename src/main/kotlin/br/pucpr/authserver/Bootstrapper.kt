package br.pucpr.authserver

import br.pucpr.authserver.roles.Role
import br.pucpr.authserver.roles.RoleRepository
import br.pucpr.authserver.users.User
import br.pucpr.authserver.users.UserRepository
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class Bootstrapper(
    private val rolesRepository: RoleRepository,
    private val userRepository: UserRepository,
): ApplicationListener<ContextRefreshedEvent> {
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        val adminRole = rolesRepository.findByIdOrNull("ADMIN")
            ?: rolesRepository.save(Role("ADMIN", "System Administrator"))
                .also { rolesRepository.save(Role("USER", "Premium User")) }

        if(userRepository.findByRole("ADMIN").isEmpty()) {
            val admin = User(
                email="amin@authserver.com",
                password = "admin",
                name = "Auth Server Administrator"
            )

            admin.roles.add(adminRole)
            userRepository.save(admin)
        }
    }
}