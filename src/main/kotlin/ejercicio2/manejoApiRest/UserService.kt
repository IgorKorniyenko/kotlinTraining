package ejercicio2.manejoApiRest

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @GET("user/{id}")
    suspend fun getUser(@Path("id") id: Int): Response<User>

    @PUT("user")
    suspend fun updateUser(@Body user: User): Response<User>

    @POST("user")
    suspend fun createUser(@Body user: User): Response<User>

    @DELETE("user/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<Void>

}