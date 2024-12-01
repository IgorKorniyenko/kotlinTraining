package ejercicio1.validacionFormularios

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ValidatorTest {

    @Test
    fun `validate should return Success for valid name, email and password`() {
        val validator = Validator()

        val result = validator.validate("Igor", "igor@mail.com", "Seik0Watch")

        assertTrue(result is Validator.ValidationResult.Success)

    }

    @Test
    fun `validate should return Error if name has less than 3 characters`() {
        val validator = Validator()
        val result = validator.validate("Bo", "igor@mail.com", "Seik0Watch")

        assertTrue(result is Validator.ValidationResult.Errors)

        if (result is Validator.ValidationResult.Errors) {
            assertEquals("Name must contain at least 3 letters", result.errors["name"] )
        }
    }

    @Test
    fun `validate should return Error if email format is incorrect`() {
        val validator = Validator()
        val result = validator.validate("Igor", "igormail.com", "Seik0Watch")

        assertTrue(result is Validator.ValidationResult.Errors)

        if (result is Validator.ValidationResult.Errors) {
            assertEquals("Incorrect email format", result.errors["email"] )
        }
    }

    @Test
    fun `validate should return Error if password format is incorrect`() {
        val validator = Validator()
        val result = validator.validate("Igor", "igor@mail.com", "123")

        if(result is Validator.ValidationResult.Errors) {
            assertEquals("Password must have at least: one upper letter, one lower letter, one digit", result.errors["password"])
        }
    }




}