import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun hello(): String {
    return "Hello, world!"
}
data class AddResult(val first: Int, val second: Int, val result: Int)
// return an instance of AddResult

fun main() {
    embeddedServer(Netty, 8080) {
        routing {
            get("/") {
                call.respondText(hello())
            }
            get("/breakroom") {
                call.respondText("Welcome to the Break Room.")
            }
            get("/add/{first}/{second}") {
                try {
                    val first = call.parameters["first"]!!.toInt()
                    val second = call.parameters["second"]!!.toInt()
                    //those lines of code refer to literal values the user types in the
                    //url bar
                    call.respondText((first + second).toString())
                } catch (e: Exception) {
                    println(e)
                    call.respond(HttpStatusCode.BadRequest)
                }

            }
        }
    }.start(wait = true)
}
