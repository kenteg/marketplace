package sample.view

import react.*
import react.dom.*
import react.router.dom.hashRouter
import react.router.dom.redirect
import react.router.dom.route
import react.router.dom.switch


class App : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        val isAuth =  true
        div("App") {
                hashRouter {
                    switch {
                        if (!isAuth) {
                           // route("/auth", Auth::class, true)
                        } else {
                            route("/catalog") { catalog("Products")  }
                            route("/ticker") { ticker()  }
                        }

                        if (!isAuth) {
                            redirect("/", "/auth")
                        } else {
                            redirect("/", "/ticker")
                        }

                    }


                }


            }

    }
}
fun RBuilder.app() = child(App::class) {}