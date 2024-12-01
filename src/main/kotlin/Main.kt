import ejercicio2.manejoApiRest.UsersRetriever
import kotlinx.coroutines.runBlocking


fun main()  = runBlocking {
    val userRetriever = UsersRetriever()
    userRetriever.run()
}