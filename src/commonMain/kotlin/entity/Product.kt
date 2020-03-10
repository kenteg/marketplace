package entity


data class Product (
    val name: String,
    val code: String,
    val price: String,
    val brand: Brand?,
    val category: ProductCategory?,
    val image: ByteArray?,
    val description: String
)