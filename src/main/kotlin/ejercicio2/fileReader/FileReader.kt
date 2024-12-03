package ejercicio2.fileReader

import ejercicio2.exceptions.UserParseException
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class FileReader {

    /**
    sealed class UserParseException(message: String): Exception(message) {
        class IncorrectIdException(message: String): UserParseException(message)
        class IncorrectEmailException(message: String): UserParseException(message)
        class IncorrectBirthDateException(message: String): UserParseException(message)
        class EmptyFileException(message: String): UserParseException(message)
    }
    **/

    fun readFile(filePath: String) {
        try {
            val content = File(filePath).readLines()

            if (content.isEmpty()) {
                throw UserParseException("Read error", "File is empty")
            }

            translateUsers(content).forEach { user ->
                println(user)
            }

        }catch (e: UserParseException) {
            println(e.message)
        } catch (e: Exception) {
            println("Read error: ${e.message}")
        }
    }

    private fun translateUsers(users: List<String>): List<User> {
        val translatedUsers = mutableListOf<User>()
        val errorFilePath = "C:\\Users\\ptnhl\\Documents\\Projects\\KotlinTraining\\src\\main\\kotlin\\ejercicio2\\read_errors.txt"
        val errors = mutableListOf<String>()

        users.forEachIndexed { index, line ->
            val rawUserData = line.split(",").map { it.trim() }

            if (rawUserData.size != 6) {
                errors.add("Line ${index + 1}: Some user data is missing")
                return@forEachIndexed
            }

            try {
                val user = parseUser(rawUserData)
                translatedUsers.add(user)
            } catch (e: UserParseException) {
                errors.add("Line ${index + 1}: ${e.message}")
            } catch (e: Exception) {
                errors.add("Line ${index + 1}: Unexpected error ${e.message}")
            }

            if (errors.isNotEmpty()) {
                File(errorFilePath).writeText(errors.joinToString("\n"))
            }
        }

        return translatedUsers
    }

    private fun parseUser(rawUserData: List<String>): User {
        try {
            val id = rawUserData[0].toIntOrNull() ?: throw UserParseException("Parse error","Incorrect user ID")
            val name = rawUserData[1]
            val email = validateEmail(rawUserData[2].trim()) ?: throw UserParseException("Parse error", "Incorrect email format")
            val birthDate = formatBirthDate(rawUserData[3].trim()) ?: throw UserParseException("Parse error", "Incorrect date format")
            val location = rawUserData[4]
            val profession = rawUserData[5]

            return User(id, name, email, birthDate, location, profession)

        } catch (e: Exception) {
            throw e
        }

    }

    private fun validateEmail(rawEmail: String): String? {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
        return if (emailRegex.matches(rawEmail)) rawEmail else null
    }

    private fun formatBirthDate(rawBirthDate: String): LocalDate? {
        return try {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            LocalDate.parse(rawBirthDate, formatter)
        } catch(e: DateTimeParseException) {
            null
        }
    }
}

fun main() {
    val fileReader = FileReader()

    fileReader.readFile("C:\\Users\\ptnhl\\Documents\\Projects\\KotlinTraining\\src\\main\\kotlin\\ejercicio2\\users.txt")
}