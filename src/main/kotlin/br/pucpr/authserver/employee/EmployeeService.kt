package br.pucpr.authserver.employee

import br.pucpr.authserver.employee.controller.responses.EmployeeResponse
import br.pucpr.authserver.employee.controller.responses.LoginResponse
import br.pucpr.authserver.exception.NotFoundException
import br.pucpr.authserver.payStubs.PayStubService
import br.pucpr.authserver.roleLevels.RoleLevelService
import br.pucpr.authserver.security.JWT
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class EmployeeService(
    val employeeRepository: EmployeeRepository,
    val roleLevelService: RoleLevelService,
    val payStubService: PayStubService,
    private val jwt: JWT
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

    fun addPayStub(id: Long, payStubId: Long): Boolean {
        val user = findByIdOrNull(id) ?: throw NotFoundException("User $id not found")

        val payStub = payStubService.findByIdOrNull(payStubId) ?: throw NotFoundException("Role $payStubId not found")

        user.payStubs.add(payStub)
        employeeRepository.save(user)
        return true
    }


    fun login(email: String, password: String): LoginResponse? {
        val employee = employeeRepository.findByEmail(email) ?: return null
        if(employee.password != password) return  null
        log.info("Employee logged")

        return LoginResponse(
            token = jwt.createToken(employee),
            employee = EmployeeResponse(employee)
        )
    }

    companion object {
        val log = LoggerFactory.getLogger(JWT::class.java)
    }
}