package de.payfree.employee.client

expect fun platformInfoName() : String

class PlatformServices {
    fun name() = platformInfoName()
}