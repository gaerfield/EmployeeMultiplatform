package de.payfree.employee.server

import org.springframework.stereotype.Component

internal data class Employee(
    val id : Int,
    val name : String,
    val salary : Long,
    val age : Int,
    val profileImage : String
)

@Component
internal class EmployeeRepository {
    val employees = listOf(
            Employee(1, "Tiger Nixon", 320800L, 61, ""),
            Employee(2, "Garrett Winters", 170750L, 63, ""),
            Employee(3, "Ashton Cox", 86000L, 66, ""),
            Employee(4, "Cedric Kelly", 433060L, 22, ""),
            Employee(5, "Airi Satou", 162700L, 33, ""),
            Employee(6, "Brielle Williamson", 372000L, 61, ""),
            Employee(7, "Herrod Chandler", 137500L, 59, ""),
            Employee(8, "Rhona Davidson", 327900L, 55, ""),
            Employee(9, "Colleen Hurst", 205500L, 39, ""),
            Employee(10, "Sonya Frost", 103600L, 23, ""),
            Employee(11, "Jena Gaines", 90560L, 30, ""),
            Employee(12, "Quinn Flynn", 342000L, 22, ""),
            Employee(13, "Charde Marshall", 470600L, 36, ""),
            Employee(14, "Haley Kennedy", 313500L, 43, ""),
            Employee(15, "Tatyana Fitzpatrick", 385750L, 19, ""),
            Employee(16, "Michael Silva", 198500L, 66, ""),
            Employee(17, "Paul Byrd", 725000L, 64, ""),
            Employee(18, "Gloria Little", 237500L, 59, ""),
            Employee(19, "Bradley Greer", 132000L, 41, ""),
            Employee(20, "Dai Rios", 217500L, 35, ""),
            Employee(21, "Jenette Caldwell", 345000L, 30, ""),
            Employee(22, "Yuri Berry", 675000L, 40, ""),
            Employee(23, "Caesar Vance", 106450L, 21, ""),
            Employee(24, "Doris Wilder", 85600L, 23, "")
    ).map { it.id to it }.toMap()

    fun byId(id: Int) = employees[id]
    fun employees() = employees.values
}