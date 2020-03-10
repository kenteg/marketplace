package model.dao

import entity.Brand
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import javax.sql.rowset.serial.SerialBlob

class BrandDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BrandDao>(Brands)
    var name  by Brands.name
    var country by Brands.country
    var logo by Brands.logo.transform({ SerialBlob(it) }, { it?.getBytes(1, it.length().toInt())})

    fun toModel(): Brand = Brand(name, country, logo)
}