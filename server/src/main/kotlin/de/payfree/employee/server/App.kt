package de.payfree.employee.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.File

@SpringBootApplication
class EmployeeServerApp

fun main(args: Array<String>) {
    runApplication<EmployeeServerApp>(*args)
}
