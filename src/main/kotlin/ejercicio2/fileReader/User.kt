package ejercicio2.fileReader

import java.time.LocalDate

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val birthDate: LocalDate?,
    val location: String,
    val profession: String
)