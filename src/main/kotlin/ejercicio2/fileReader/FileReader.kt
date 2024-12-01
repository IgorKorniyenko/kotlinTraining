package ejercicio2.fileReader

import ejercicio1.validacionFormularios.Validator
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FileReader {

    sealed class UserParseException(message: String): Exception(message) {
        class IncorrectIdException(message: String): UserParseException(message)
        class IncorrectEmailException(message: String): UserParseException(message)
        class IncorrectBirthDateException(message: String): UserParseException(message)
    }
    fun readFile(filePath: String) {
        try {
            val content = File(filePath).readLines()

            if (content != null) {
                translateUsers(content).forEach { user ->
                    println(user.toString())
                }
            }
        } catch (e: Exception) {
            println("Read error")
        }
    }

    private fun translateUsers(users: List<String>): List<User> {
        val translatedUsers = mutableListOf<User>()

        users.forEach { line ->
            val rawUserData = line.split(",")

            val parseUserResponse = parseUser(rawUserData)

            if (parseUserResponse.parsedUser != null) {
                translatedUsers.add(parseUserResponse.parsedUser!!)
            }

            if (!parseUserResponse.errors.isNullOrEmpty()) {
                val errorFilePath = "C:\\Users\\ptnhl\\Documents\\Projects\\KotlinTraining\\src\\main\\kotlin\\ejercicio2\\read_errors.txt"
                parseUserResponse.errors!!.forEach { (key, value) ->
                    File(errorFilePath).appendText("$key: $value \n")
                }
            }

        }

        return translatedUsers
    }

    private fun parseUser(rawUserData: List<String>): UserParserResponse {
        var userParserResponse = UserParserResponse(null, null)
        var errors = mutableMapOf<String, String>()

        try {
            val id = rawUserData[0].toIntOrNull() ?: throw UserParseException.IncorrectIdException("Incorrect user ID")
            val name = rawUserData[1]
            val email = validateEmail(rawUserData[2]) ?: throw UserParseException.IncorrectEmailException("Incorrect email format")
            val birthDate = formatBirthDate(rawUserData[3]) ?: throw UserParseException.IncorrectBirthDateException("Incorrect date format")
            val location = rawUserData[4]
            val profession = rawUserData[5]

            val user = User(id, name, email, birthDate, location, profession)
            userParserResponse.parsedUser = user

        } catch (e: UserParseException.IncorrectIdException) {
            errors["id"] = "'${rawUserData[0]}' ${e.message.toString()}"
            userParserResponse.errors = errors
        }catch (e: UserParseException.IncorrectEmailException) {
            errors["email"] = "'${rawUserData[2]}' ${e.message.toString()}"
            userParserResponse.errors = errors
        } catch (e: UserParseException.IncorrectBirthDateException) {
            errors["birth date"] = "'${rawUserData[3]}' ${e.message.toString()}"
            userParserResponse.errors = errors
        }catch (e: Exception) {
            errors["unknown"] = "Unknown error has occurred"
            userParserResponse.errors = errors
        }

        return userParserResponse
    }

    private fun validateEmail(rawEmail: String): String? {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
        return if (emailRegex.matches(rawEmail.trim())) {
            rawEmail
        } else {
            null
        }
    }

    private fun formatBirthDate(rawBirthDate: String): LocalDate? {
        var formattedDate: LocalDate? = null

        if (rawBirthDate.trim().length == 10) {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            formattedDate = LocalDate.parse(rawBirthDate.trim(), formatter)
        }

        return formattedDate
    }
}

fun main() {
    val fileReader = FileReader()

    fileReader.readFile("C:\\Users\\ptnhl\\Documents\\Projects\\KotlinTraining\\src\\main\\kotlin\\ejercicio2\\users.txt")
}