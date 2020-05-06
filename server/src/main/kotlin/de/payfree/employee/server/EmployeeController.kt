package de.payfree.employee.server

import de.payfree.employee.api.ResponseData
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

typealias ApiEmployee = de.payfree.employee.api.Employee

@RestController
internal class EmployeeController(
        private val repository: EmployeeRepository
) {

    @GetMapping("/employees")
    fun employees() : ResponseData = ResponseData(
            data = repository.employees.values
                    .map { ApiEmployee("${it.id}", it.name, "${it.salary}", "${it.age}", it.profileImage) }
                    .toList()
    )

}