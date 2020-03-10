package model.dao

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column

object ProductCategories : IntIdTable("Product_Category"){
    val name: Column<String> = varchar("name", 250)
    val code: Column<String> = varchar("code", 50)
    val parent = reference("parent_id", id).nullable()
    val isEnabled: Column<Boolean?> = bool("is_enabled").nullable()
}