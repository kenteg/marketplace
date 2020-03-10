package model.dao

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column
import java.math.BigDecimal
import java.sql.Blob

object Products : IntIdTable("Product") {
    val name: Column<String> = varchar("name", 250)
    val code: Column<String> = varchar("code", 50).uniqueIndex()
    val image: Column<Blob?> = blob("image").nullable()
    val price: Column<BigDecimal> = decimal("price", 10, 2)
    val brand = reference("brand_id", Brands).nullable()
    val category = reference ("product_category_id", ProductCategories).nullable()
    val description: Column<String> = varchar("description", 10000)
}