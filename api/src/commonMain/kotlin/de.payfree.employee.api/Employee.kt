package de.payfree.employee.api

data class Employee(
        val id : String,
        val employee_name : String,
        val employee_salary : String,
        val employee_age : String,
        val profile_image : String
)

enum class ResponseStatus { success, failure }
data class ResponseData(
        val status: ResponseStatus = ResponseStatus.success,
        val data: Any
)