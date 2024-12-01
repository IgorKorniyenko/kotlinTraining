package ejercicio1.validacionFormularios

class Validator {

    sealed class ValidationResult {
        data class Errors(val errors: Map<String, String>) : ValidationResult()
        object Success : ValidationResult()
    }

    fun validate(name: String, email: String, password: String): ValidationResult {
        var errors = mutableMapOf<String, String>()

        if (!isValidName(name)){
            errors["name"] = "Name must contain at least 3 letters"
        }

        if (!isValidEmail(email)) {
            errors["email"] = "Incorrect email format"
        }

        if (!isValidPassword(password)) {
            errors["password"] = "Password must have at least: one upper letter, one lower letter, one digit"
        }

        return if (errors.isNotEmpty()){
            ValidationResult.Errors(errors)
        } else {
            ValidationResult.Success
        }
    }

    private fun isValidName(name: String): Boolean {
        val nameRegex = Regex(".*[A-Za-z].*[A-Za-z].*[A-Za-z].*")
        return nameRegex.matches(name)
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
        return emailRegex.matches(email)
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordRegex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$")
        return passwordRegex.matches(password)
    }
}


