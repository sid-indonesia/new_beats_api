import io.javalin.Javalin

fun main(args: Array<String>){
    val app = Javalin.create().start(8000)
    app.get("/"){ ctx -> ctx.result("Hello world!")}
}