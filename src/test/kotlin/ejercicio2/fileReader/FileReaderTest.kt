package ejercicio2.fileReader

import ejercicio2.exceptions.UserParseException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import java.time.LocalDate
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FileReaderTest {
    @Test
    fun `readFile should process a valid file with multiple lines correctly`() {
        val validContent = """
            1, Jose, jose@mail.com, 05/04/1998, París-Francia, Ingeniero de Software
            2, Maria, maria@mail.com, 12/11/1990, Madrid-España, Diseñadora Gráfica 
        """.trimIndent()
        val tempFile = createTempFile(validContent)
        val fileReader = FileReader()

        val expectedUsers = listOf(
            User(1, "Jose", "jose@mail.com", LocalDate.of(1998, 4, 5), "París-Francia", "Ingeniero de Software"),
            User(2, "Maria", "maria@mail.com", LocalDate.of(1990, 11, 12), "Madrid-España", "Diseñadora Gráfica")
        )

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        fileReader.readFile(tempFile.absolutePath)

        val output = outputStream.toString().trim()
        expectedUsers.forEach { user ->
            assertTrue(output.contains(user.toString()), "Output do not have a user: $user")
        }

        System.setOut(System.out)
    }

    @Test
    fun `readFile should throw UserParseException when file is empty`() {
        val emptyFile = createTempFile("")
        val fileReader = FileReader()

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        fileReader.readFile(emptyFile.absolutePath)

        val output = outputStream.toString().trim()

        assertTrue(output.contains("File is empty"), "Empty files must notify about it to the users")

        System.setOut(System.out)
    }

    @Test
    fun `readFile should process a file with a single valid line correctly`() {
        val singleLineContent = "1, Jose, jose@mail.com, 05/04/1998, París-Francia, Ingeniero de Software"
        val tempFile = createTempFile(singleLineContent)
        val fileReader = FileReader()

        val user = User(1, "Jose", "jose@mail.com", LocalDate.of(1998, 4, 5), "París-Francia", "Ingeniero de Software")

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        fileReader.readFile(tempFile.absolutePath)

        val output = outputStream.toString().trim()

        assertEquals(user.toString(), output)

        System.setOut(System.out)
    }

    private fun createTempFile(content: String): File {
        val tempFile = kotlin.io.path.createTempFile().toFile()
        tempFile.writeText(content)
        return tempFile
    }
}