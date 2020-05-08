package de.payfree.employee.server

import de.payfree.employee.api.ResponseData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.seconds

typealias ApiEmployee = de.payfree.employee.api.Employee

@RestController
internal class EmployeeController(
        private val repository: EmployeeRepository
) {

    @GetMapping("/employees")
    fun employees() : ResponseData = ResponseData(
            data = repository.employees.values
                    .map { it.convertToApi() }
                    .toList()
    )

    @GetMapping("/employeesList")
    fun employeesList() : List<ApiEmployee> = repository.employees.values
            .map { it.convertToApi() }
            .toList()

    @GetMapping("/employees/{id}")
    fun employeesById(@PathVariable id : String) = repository.employees.values
        .filter { "${it.id}" == id }
        .map { it.convertToApi() }
        .firstOrNull()

    @ExperimentalTime
    @ExperimentalCoroutinesApi
    @GetMapping("/employeesAsync")

    suspend fun asynchronousEmployeesListArtificiallyDelayed() : Flow<ApiEmployee> = channelFlow {
        repository.employees.values.forEach {
            delay((Math.random()*10).seconds.toLongMilliseconds());
            send(it.convertToApi())
        }
    }

    @GetMapping("/unauthorized")
    fun willErrorInUnauthorized() : ApiEmployee = throw ResponseStatusException(HttpStatus.UNAUTHORIZED)

    private fun Employee.convertToApi() =
        ApiEmployee("$id", name, "$salary", "$age", profileImage)
}