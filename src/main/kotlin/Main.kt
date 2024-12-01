import ejercicio2.manejoApiRest.UsersRetriever
import io.github.aakira.napier.Napier
import kotlinx.coroutines.runBlocking


fun main()  = runBlocking {
    //val client = Client()
    // client.run()
    /**
    val validator = Validator()

    when (val result = validator.validate(
    "Igor",
    "ikorniyenko@minsait.com",
    "Seik0Watch"
    )
    ) {
    is Validator.ValidationResult.Success -> { println("Form successfully validated") }
    is Validator.ValidationResult.Errors -> { result.errors.forEach() {(field, error) -> println("$field: $error") }}
    }
     **/
    /**
    val validator = Validator()

    when (val result = validator.validate(
    "Igor",
    "ikorniyenko@minsait.com",
    "Seik0Watch"
    )
    ) {
    is Validator.ValidationResult.Success -> { println("Form successfully validated") }
    is Validator.ValidationResult.Errors -> { result.errors.forEach() {(field, error) -> println("$field: $error") }}
    }
     **/

    /**
    val validator = Validator()

    when (val result = validator.validate(
    "Igor",
    "ikorniyenko@minsait.com",
    "Seik0Watch"
    )
    ) {
    is Validator.ValidationResult.Success -> { println("Form successfully validated") }
    is Validator.ValidationResult.Errors -> { result.errors.forEach() {(field, error) -> println("$field: $error") }}
    }
     **/

    /**
    val validator = Validator()

    when (val result = validator.validate(
    "Igor",
    "ikorniyenko@minsait.com",
    "Seik0Watch"
    )
    ) {
    is Validator.ValidationResult.Success -> { println("Form successfully validated") }
    is Validator.ValidationResult.Errors -> { result.errors.forEach() {(field, error) -> println("$field: $error") }}
    }
     **/


    Napier.e("Error")
    val userRetriever = UsersRetriever()
    userRetriever.run()
}