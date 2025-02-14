//package br.pucpr.authserver
//
//import br.pucpr.authserver.levels.Level
//import br.pucpr.authserver.levels.LevelRepository
//import br.pucpr.authserver.roles.Role
//import br.pucpr.authserver.roles.RoleRepository
//import br.pucpr.authserver.rolesLevels.RoleLevel
//import br.pucpr.authserver.rolesLevels.RoleLevelRepository
//import br.pucpr.authserver.users.User
//import br.pucpr.authserver.users.UserRepository
//import org.springframework.context.ApplicationListener
//import org.springframework.context.event.ContextRefreshedEvent
//import org.springframework.data.repository.findByIdOrNull
//import org.springframework.stereotype.Component
//
//@Component
//class Bootstrapper(
//    private val rolesRepository: RoleRepository,
//    private val levelRepository: LevelRepository,
//    private val roleLevelRepository: RoleLevelRepository,
//    private val userRepository: UserRepository,
//): ApplicationListener<ContextRefreshedEvent> {
//    override fun onApplicationEvent(event: ContextRefreshedEvent) {
//        val adminRole = rolesRepository.findByIdOrNull("ADMIN")
//            ?: rolesRepository.save(Role("ADMIN", "System Administrator"))
//
//        val adminLevel = levelRepository.findByIdOrNull(1)
//            ?: levelRepository.save(Level(1,"Sênior", "Master System Administrator"))
//
//        val adminRoleLevel = roleLevelRepository.findByIdOrNull(2)
//            ?: roleLevelRepository.save(RoleLevel(2,adminRole, adminLevel))
//
//        if(userRepository.findByRoleLevel(2).isEmpty()) {
//            val admin = User(
//                email="amin@authserver.com",
//                password = "admin",
//                name = "Auth Server Administrator",
//                roleLevel = adminRoleLevel
//            )
//
//            userRepository.save(admin)
//        }
//    }
//}