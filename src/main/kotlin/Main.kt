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
import executor.*

fun main(args: Array<String>){
    DBConfig.init()
    val mapper = jacksonObjectMapper()
    val app = Javalin.create().start(8000)
    app.get("/"){ ctx -> ctx.result("Hello world!")}

    app.post("/session"){call ->
        val data = call.bodyAsClass(Sessions::class.java)
        val response = mapper.writeValueAsString(SessionExecutor.insertData(data))
        call.result(response).contentType("application/json")
    }

    app.get("/session"){
        var res = mapOf("session" to "aji")
        it.result(res.toString())
    }

    app.post("/response"){call->
        val data = call.bodyAsClass(ResponseData::class.java)
        val response = mapper.writeValueAsString(ResponseExecutor.insertData(data))
        call.result(response).contentType("application/json")
    }

    app.post("/participant"){call ->
        val data = call.bodyAsClass(ParticipantData::class.java)
        val response = mapper.writeValueAsString(ParticipantExecutor.insertData(data))
        call.result(response).contentType("application/json")
    }
}
