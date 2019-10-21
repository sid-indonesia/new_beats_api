import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.lang.Exception

fun main(args: Array<String>) {


    val userDao = UserDao()

    val app = Javalin.create().apply {
        exception(Exception::class.java){ e, ctx -> e.printStackTrace()}
        error(404){ctx->ctx.json("not found")}
    }.start(8000)

    Database.Companion.connect("jdbc:sqlite:/data/data.db", "org.sqlite.JDBC")

    app.routes {
        get("/users"){ ctx ->
            ctx.json(userDao.users)
        }
        
    }


}