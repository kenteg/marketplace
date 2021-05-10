package sample.view

import kotlinx.css.*
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.h1
import styled.css
import styled.styledButton
import styled.styledDiv

interface ProductPreviewProps : RProps {
    var title: String
    var imageUrl: Image
    var shortDescription: String?
}

interface ProductPreviewState : RState {
    var stage: Int
}

class ProductPreview(props: ProductPreviewProps) : RComponent<ProductPreviewProps, ProductPreviewState>() {
    override fun RBuilder.render() {

        styledDiv {
            css {
                borderRadius = LinearDimension("10px")
                borderStyle = BorderStyle.solid
                backgroundColor = Color.aliceBlue
                marginBottom = LinearDimension("10px")
                overflow = Overflow.hidden
            }

            h1 {
                +props.title
            }

            styledButton {
                css {
                    float = Float.left
                    marginBottom = LinearDimension("2px")
                    marginLeft = LinearDimension("2px")
                    borderRadius = LinearDimension("10px")
                    backgroundColor = Color.lemonChiffon
                    borderStyle = BorderStyle.solid

                }
               // attrs.onClickFunction = props.backClick
                +
                "Buy"
            }

        }


    }

}


fun RBuilder.prodPreview(title: String = "Hello!" ) = child(ProductPreview::class) {
    console.log("preview:" + title)
    attrs.title = title
    attrs.shortDescription
    attrs.imageUrl
}


