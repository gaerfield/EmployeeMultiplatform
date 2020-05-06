package de.payfree.employee.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EmployeeServerApp

fun main(args: Array<String>) {
    runApplication<EmployeeServerApp>(*args)
}