package route

import entity.Product
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.resource
import io.ktor.http.content.static
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import kotlinx.html.*
import model.dao.*
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import sample.Sample
import sample.hello
import java.math.BigDecimal

object Routing {

    fun route(application: Application): Routing {
        return application.routing {

            get("/") {
                call.respondHtml {
                    head {
                        title("Hello from Ktor!")
                    }
                    body {
                        +"${hello()} from Ktor. Check me value: ${Sample().checkMe()}"
                        div {
                            id = "js-response"
                            +"Loading..."
                        }
                        script(src = "/static/marketplace.js") {}
                    }
                }
            }

            get("/products") {
                val limit = call.request.queryParameters["limit"]?.toInt() ?: 10
                var page = call.request.queryParameters["page"]?.toInt() ?: 0
                call.respond(
                    transaction {
                        addLogger(StdOutSqlLogger)
                        model.dao.ProductDao.all().limit(limit, ++page).map { it.toModel() }
                    }
                )
            }

            post("/addProduct") {
                val product = call.receive(Product::class)
                transaction {
                    Products.insert { prod ->
                        prod[name] = product.name
                        prod[code] = product.code
                        prod[description] = product.description
                        prod[price] = BigDecimal(product.price)
                        prod[brand] = BrandDao.find { Brands.name eq (product.brand?.name!!) }.first().id
                        prod[category] =
                            ProductCategoryDao.find { ProductCategories.code eq (product.category?.code!!) }.first().id
                    }
                }
                call.respond(HttpStatusCode.OK)
            }
            static("/static") {
                resource("marketplace.js")
            }
        }
    }
}