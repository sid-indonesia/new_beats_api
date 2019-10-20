import io.javalin.Javalin
import models.Participant
import models.Response
import models.Session
import org.eclipse.jetty.util.log.Log
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import com.fasterxml.jackson.core.*
import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import executor.SessionExecutor
import executor.Sessions

fun main(args: Array<String>){
    val path = System.getProperty("user.dir")
    println(path)
    Database.connect("jdbc:sqlite:${path}/data/beats.db", "org.sqlite.JDBC")
    val app = Javalin.create().start(8000)
    app.get("/"){ ctx -> ctx.result("Hello world!")}

    app.post("/session"){ctx ->
        val data = ctx.bodyAsClass(Sessions::class.java)
        SessionExecutor.createSession(data)
    }

    app.get("/session"){
        var res = mapOf("session" to "aji")
        it.result(res.toString())
    }
}
