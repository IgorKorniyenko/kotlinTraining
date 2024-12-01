package ejercicio1.apiSimulada

import utils.ValoresAleatorios
import kotlin.random.Random

class Api() {
     private var productList: MutableList<Product> = mutableListOf()

    init {
        loadProducts()
    }

    private fun loadProducts() {
        val utils = ValoresAleatorios()

        for(productName in utils.listaProductos) {
            val price = Random.nextDouble(0.99, 10.00)
            val stock = Random.nextInt(1, 20)

            productList.add(Product(name = productName, price = price, stock = stock))
        }
    }


    fun getProducts(): Response<List<Product>> {
        var response: Response<List<Product>> = Response(404, "Products not found")

        if (productList.isNotEmpty()) {
            response = Response(200, "OK", productList)
        }

        return response
    }

    fun addProduct(providedProduct: Product): Response<Product> {
        var response: Response<Product> = Response(500, "Product with this name already exists")

        val existingProduct: Product? = productList.firstOrNull { it.name == providedProduct.name }

        if (existingProduct == null) {
            val newProduct = Product(name = providedProduct.name, price = providedProduct.price, stock = providedProduct.stock)
            productList.add(newProduct)
            response = Response(200, "Product successfully added", newProduct)
        }

        return response
    }

    fun updateProduct(providedProduct: Product): Response<Product> {
        var response: Response<Product> = Response(404, "Product with provided ID do not exists in the store")

        val existingProduct: Product? = productList.lastOrNull() { it.id == providedProduct.id}

        if (existingProduct != null) {
            val index = productList.indexOf(providedProduct)
            productList[index] = providedProduct

            response = Response(200, "Product was successfully updated", providedProduct)
        }

        return response
    }

    fun getProduct(searchText: String): Response<Product> {
        var response: Response<Product> = Response(404, "Could not find products with the search word")

        val searchedProduct = productList.firstOrNull() { it.name.contains(searchText, ignoreCase = true)}

        if (searchedProduct != null) {
            response = Response(200, "OK", searchedProduct)
        }

        return response
    }


}