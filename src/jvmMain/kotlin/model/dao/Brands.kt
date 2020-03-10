package model.dao

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column
import java.sql.Blob

object Brands : IntIdTable("Brand"){
    val name: Column<String> = varchar("name", 250)
    val country: Column<String> = varchar("country", 2)
    val logo: Column<Blob?> = blob("logo").nullable()
}