package ejercicio2.manejoApiRest


import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


class UsersRetriever {

    var apiService = RetrofitClient.instance.create(UserService::class.java)

    sealed class ApiException(message: String) : Exception(message) {
        class NetworkException(message: String) : ApiException(message)
        class ServerException(message: String) : ApiException(message)
        class UnknownException(message: String) : ApiException(message)
    }
    suspend fun run() {

        fetchUsers()?.let { users ->
            println("Users has been fetched")
            users.forEach { println(it) }
        } ?: println("No users found")

        fetchUser(1)?.let {user ->
            println("User has been found \n $user")
        } ?: println("Can't locate a user with this id")


        val userToUpdate = User(1, "Igor", "igor@gmail.com", "Seik0Watch")
        updateUser(userToUpdate)?.let {  user ->
            println("User ${user.name} has been updates successfully")
        } ?: println("Error occurred when updating user")


        val userToCreate = User(5, "Jose", "jose@mail.com", "AW123123")
        createUser(userToCreate)?.let { user ->
            println("User ${user.name} has been created")
        } ?: println("Error occurred during user creation")


        if (deleteUser(1)) {
            println("User has been deleted")
        } else {
            println("Error occurred when deleting user")
        }

    }


    private suspend fun <T> safeApiCall(call: suspend () -> Response<T>): T? {
        return try {
            val response = call()
            if (response.isSuccessful) {
                response.body()
            } else {
                val exception = when (response.code()) {
                    in 500..599 -> ApiException.ServerException("Server error: ${response.code()}")
                    in 400..499 -> ApiException.NetworkException("Client error: ${response.code()}")
                    else -> ApiException.UnknownException("Unknown error: ${response.code()}")
                }

                throw exception
                println("API error: ${response.code()} - ${response.message()}")
                null
            }
        } catch (e: ApiException) {
            println("API exception detected: ${e.message}")
            null
        } catch (e: Exception) {
            println("Unexpected exception")
            null
        }
    }

    suspend fun fetchUsers() : List<User>? {
        return safeApiCall { apiService.getUsers() }
    }

    private suspend fun fetchUser(id: Int) : User? {
        return safeApiCall { apiService.getUser(id) }
    }

    private suspend fun updateUser(user: User): User? {
        return safeApiCall { apiService.updateUser(user) }
    }

    private suspend fun createUser(user: User): User? {
        return safeApiCall { apiService.createUser(user) }
    }

    private suspend fun deleteUser(id: Int): Boolean {
        return safeApiCall { apiService.deleteUser(id) } != null
    }

}
object RetrofitClient {
    private const val BASE_URL = "https://usersapi.free.beeceptor.com/api2/"

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}