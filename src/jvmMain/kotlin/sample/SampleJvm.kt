package sample

import com.fasterxml.jackson.databind.SerializationFeature
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.http.HttpMethod.Companion.Delete
import io.ktor.http.HttpMethod.Companion.Options
import io.ktor.http.HttpMethod.Companion.Patch
import io.ktor.http.HttpMethod.Companion.Put
import io.ktor.jackson.jackson
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.netty.handler.codec.http.HttpMethod
import liquibase.Contexts
import liquibase.Liquibase
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import org.jetbrains.exposed.sql.Database
import route.Routing.route

actual class Sample {
    actual fun checkMe() = 42
}

actual object Platform {
    actual val name: String = "JVM"
}

fun main() {
    val hc = HikariConfig("/hikari.properties")

    val ds = HikariDataSource(hc)
    val db = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(JdbcConnection(ds.connection))

    Database.connect(ds)
    val liq = Liquibase("changelog-master.yaml", ClassLoaderResourceAccessor(), db)
    liq.update(Contexts())

    embeddedServer (Netty,
        host = "127.0.0.1",
        port = 8080,
        watchPaths = listOf("jsMain"),
        module = Application::config
    ).start(wait = true)
}


fun Application.config(){
        install(ContentNegotiation) {
            jackson {
                enable(SerializationFeature.INDENT_OUTPUT)
            }
        }
        install(CORS){
            method(Options)
            method(Put)
            method(Delete)
            method(Patch)

            // Beware that this is not recommended for production,
            // but I'm just using it during development
            anyHost()
        }

        route(this)
}