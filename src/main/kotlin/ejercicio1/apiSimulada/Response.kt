package ejercicio1.apiSimulada

data class Response<T>(
    val code: Int,
    val message: String,
    val data: T? = null
)