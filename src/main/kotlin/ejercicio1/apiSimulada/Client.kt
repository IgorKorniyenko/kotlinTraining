package ejercicio1.apiSimulada

class Client {
    fun run() {
        val api = Api()

        //Retrieving products list
        val getProductsResponse = api.getProducts()
        println("${getProductsResponse.code} : ${getProductsResponse.message}")

        if(!getProductsResponse.data.isNullOrEmpty()) {
            for(product in getProductsResponse.data!!) {
                println(product.toString())
            }
        }


        //Adding new product
        val newProduct = Product(name = "Arroz", price = 0.20, stock = 8)
        val addProductResponse = api.addProduct(newProduct)
        println("${addProductResponse.code} : ${addProductResponse.message}")


        //Updating existing product
        val productToUpdate = api.getProduct("toma").data

        if (productToUpdate != null) {
            productToUpdate.stock = 20

            val updatingProductResponse = api.updateProduct(productToUpdate)
            println("${updatingProductResponse.code} : ${updatingProductResponse.message}")
        }

        //Searching product by name
        val searchProductResponse = api.getProduct("ch")
        println("${searchProductResponse.code} : ${searchProductResponse.message}")

        if (searchProductResponse.data != null) {
            println(searchProductResponse.data.toString())
        }
    }
}