package ejercicio1.apiSimulada

import java.util.UUID

data class Product(
    val id: String = UUID.randomUUID().toString(),
    var name: String,
    var price: Double,
    var stock: Int
)