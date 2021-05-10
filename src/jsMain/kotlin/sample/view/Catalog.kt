package sample.view

import entity.Product
import kotlinx.coroutines.*
import kotlinx.css.*
import react.*
import react.dom.h1
import sample.network.getAndParseResult
import styled.css
import styled.styledDiv

interface CatalogProps : RProps {
    var title: String
}

interface CatalogState : RState {
    var items: MutableList<Product>
}

class Catalog(props: CatalogProps) : RComponent<CatalogProps, CatalogState>() {

    override fun RBuilder.render() {
        styledDiv {
            h1 {
                + props.title
            }

            css {
                display = Display.grid
                gridTemplateColumns = GridTemplateColumns.repeat("5, 1fr")
                gridTemplateRows = GridTemplateRows.repeat("10, 100%")


                }
            val prods=state.items.toTypedArray()
            for (item in prods) {
                styledDiv {
                    prodPreview(item.name)
                }
            }
        }

    }

    override fun componentDidMount() {
        GlobalScope.launch {
            this@Catalog.state.items = withContext(Dispatchers.Default) {
                getAndParseResult("http://localhost:8080/products?limit=10&page=0", null) {
                    console.log("LST: $it")
                    JSON.parse(it as String)
                }
            }
        }

    }

    override fun CatalogState.init() {
        items = mutableListOf()
    }
}


fun RBuilder.catalog(title: String = "Hello!") = child(Catalog::class) {
    attrs.title = title
}


