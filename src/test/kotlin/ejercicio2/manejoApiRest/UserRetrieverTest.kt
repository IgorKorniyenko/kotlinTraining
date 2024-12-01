package ejercicio2.manejoApiRest

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import io.mockk.*
import kotlinx.coroutines.test.runTest
import retrofit2.Response

class UserRetrieverTest {

    private val mockApiService = mockk<UserService>()
    private val usersRetriever = UsersRetriever().apply {
        apiService = mockApiService
    }

    @Test
    fun `fetchUsers should return a list of users when API call is successful`() = runTest {
        val mockUsers = listOf(
            User(1, "John Doe", "john@mail.com", "password123"),
            User(2, "Jane Doe", "jane@mail.com", "securePass")
        )

        coEvery { mockApiService.getUsers() } returns Response.success(mockUsers)

        val users = usersRetriever.fetchUsers()

        assertNotNull(users)
        assertEquals(2, users?.size)
        assertEquals("John Doe", users?.first()?.name)

    }

    @Test
    fun `fetchUsers should return null and throw ServerException on HTTP 500`() = runTest {
        coEvery { mockApiService.getUsers() } returns Response.error(500, mockk(relaxed = true))

        val users = try {
            usersRetriever.fetchUsers()
        } catch (e: UsersRetriever.ApiException.ServerException) {
            assertEquals("Server error: 500", e.message)
            null
        }

        assertNull(users)
    }

    @Test
    fun `fetchUsers should handle unexpected exceptions gracefully`() = runTest {
        coEvery { mockApiService.getUsers() } throws Exception("Network timeout")

        val users = usersRetriever.fetchUsers()

        assertNull(users)
    }
}