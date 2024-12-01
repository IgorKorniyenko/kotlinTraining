package ejercicio2.manejoRest

import ejercicio2.manejoApiRest.RetrofitClient
import ejercicio2.manejoApiRest.UserService
import ejercicio2.manejoApiRest.UsersRetriever
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
class UserRetrieverTest {

    val api = UsersRetriever()

    @Test
    fun `fetch users must return null when api isn't reachable`() = runBlocking {
        assertEquals(false, api.fetchUsers())

    }
}