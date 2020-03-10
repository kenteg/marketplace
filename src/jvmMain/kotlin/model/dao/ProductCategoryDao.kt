package model.dao

import entity.ProductCategory
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class ProductCategoryDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ProductCategoryDao>(ProductCategories)

    var name: String by ProductCategories.name
    var code: String by ProductCategories.code
    var parent: ProductCategoryDao? by ProductCategoryDao optionalReferencedOn ProductCategories.parent


    fun toModel() = ProductCategory(name, code)
}