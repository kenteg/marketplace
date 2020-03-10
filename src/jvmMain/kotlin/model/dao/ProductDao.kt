package model.dao

import entity.Product
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import java.math.BigDecimal
import javax.sql.rowset.serial.SerialBlob

class ProductDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ProductDao>(Products)

    var name: String by Products.name
    var code: String by Products.code
    var image: ByteArray? by Products.image.transform({ SerialBlob(it) }, { it?.getBytes(1, it.length().toInt()) })
    var price: BigDecimal by Products.price
    var brand by BrandDao optionalReferencedOn Products.brand
    var category by ProductCategoryDao optionalReferencedOn Products.category
    var description: String by Products.description

    fun toModel() = Product(name, code, price.toString(), brand?.toModel(), category?.toModel(), image, description)
}