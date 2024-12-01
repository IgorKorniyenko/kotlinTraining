package ejercicio1.apiSimulada

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ApiTest {

    private val api = Api()

    @Test
    fun `getProducts should return products when list is not empty`() {
        val response = api.getProducts()
        assertEquals(200, response.code)
        assertNotNull(response.data)
        assertTrue(response.data!!.isNotEmpty(), "La lista de productos no debería estar vacía")
    }

    @Test
    fun `addProducto should add a new product successfully`() {
        val nuevoProducto = Product(name = "Harina", price = 3.99, stock = 20)
        val response = api.addProduct(nuevoProducto)
        assertEquals(200, response.code)
        assertEquals("Product successfully added", response.message)
        assertTrue(api.getProducts().data!!.any { it.name == "Harina" }, "Product with this name already exists")
    }

    @Test
    fun `addProducto should not add a duplicate product`() {
        val nuevoProducto = Product(name = "Leche", price = 2.50, stock = 10)
        api.addProduct(nuevoProducto) // Añadimos por primera vez
        val response = api.addProduct(nuevoProducto) // Intentamos añadirlo de nuevo
        assertEquals(500, response.code)
        assertEquals("Product with this name already exists", response.message)
    }

    @Test
    fun `updateProducto should update stock for an existing product`() {
        val product = api.getProducts().data!!.first() // Tomamos el primer producto
        val nuevoStock = 50
        val response = api.updateProduct(product)
        assertEquals(200, response.code)
        assertEquals("Product was successfully updated", response.message)
    }

    @Test
    fun `updateProducto should return 404 for non-existent product`() {
        val response = api.updateProduct(Product(id = "inexistente", name = "X", price = 0.00, stock = 0))
        assertEquals(404, response.code)
        assertEquals("Product with provided ID do not exists in the store", response.message)
    }

    @Test
    fun `buscarProductos should find products matching text`() {
        val response = api.getProduct("Le")
        assertEquals(200, response.code)
        assertNotNull(response.data)
        assertTrue(response.data!! != null, "Debería encontrar productos coincidentes")
        //assertTrue(response.data.name.contains("Le"))
    }

    @Test
    fun `buscarProductos should return 404 when no products match`() {
        val response = api.getProduct("XYZ")
        assertEquals(404, response.code)
        assertEquals("Could not find products with the search word", response.message)
        assertNull(response.data)
    }
}
