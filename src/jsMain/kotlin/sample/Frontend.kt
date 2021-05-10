package sample

import kotlinx.browser.*
import kotlinx.css.Contain
import kotlinx.css.a
import kotlinx.css.body
import kotlinx.css.div
import kotlinx.html.*
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.render
import sample.view.App
import sample.view.app


actual class Sample {
    actual fun checkMe() = 12
}

actual object Platform {
    actual val name: String = "JS"
}

fun main() {
    render(document.getElementById("js-response")) {
        app()
    }


}

