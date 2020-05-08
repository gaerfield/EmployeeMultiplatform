package de.payfree.employee.api

import kotlinx.serialization.Serializable

interface Account {
    val id: Number
}

@Serializable
data class Employee(
        val id : String,
        val employee_name : String,
        val employee_salary : String,
        val employee_age : String,
        val profile_image : String
)

@Serializable
enum class ResponseStatus { success, failure }

@Serializable
data class ResponseData(
        val status: ResponseStatus = ResponseStatus.success,
        val data: List<Employee>
)