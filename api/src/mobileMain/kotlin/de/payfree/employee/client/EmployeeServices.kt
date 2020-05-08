package de.payfree.employee.client

import de.payfree.employee.api.Account
import de.payfree.employee.api.Employee
import de.payfree.employee.api.ResponseData
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

class EmployeeServices(
    private val endpoint: String = "http://localhost:8080"
) {
    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun employees()= coroutineScope {
        async { client.get<ResponseData>("$endpoint/employees") }.await().data
    }

    suspend fun employeesList() =
        client.get<List<Employee>>("$endpoint/employeesList")

    suspend fun accounts() =
            client.get<List<Account>>("$endpoint/accounts")

    suspend fun employeeById(id : String) =
        client.get<Employee>("$endpoint/employees/$id")

    suspend fun asynchronousListEmittingDataDelayed() =
        client.get<Flow<Employee>>("$endpoint/employeesAsync")

    suspend fun willThrowAnError() =
        client.get<Employee>("$endpoint/unauthorized")

}