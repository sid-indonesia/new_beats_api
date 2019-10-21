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
    app.get("/"){ ctx -> ctx.result("Beats Api Service V1")}

//    Sessions activity here
    app.post("/sessions"){call ->
        val data = call.bodyAsClass(Sessions::class.java)
        val response = mapper.writeValueAsString(SessionExecutor.insertData(data))
        call.result(response).contentType("application/json")
    }

    app.get("/sessions"){ call ->
        call.result(mapper.writeValueAsString(SessionExecutor.selectAll())).contentType("application/json")
    }

//    Response Activity here
    app.post("/responses"){call->
        val data = call.bodyAsClass(ResponseData::class.java)
        val response = mapper.writeValueAsString(ResponseExecutor.insertData(data))
        call.result(response).contentType("application/json")
    }

    app.get("/responses"){call ->
        call.result(mapper.writeValueAsString(ResponseExecutor.selectAll())).contentType("application/json")
    }

    app.get("/response/?uid=uid"){
        print(it.pathParam("uid"))
    }

//    Participants activity here
    app.post("/participants"){call ->
        val data = call.bodyAsClass(ParticipantData::class.java)
        val response = mapper.writeValueAsString(ParticipantExecutor.insertData(data))
        call.result(response).contentType("application/json")
    }

    app.get("/participants"){call->
        call.result(mapper.writeValueAsString(ParticipantExecutor.selectAll())).contentType("application/json")
    }
}
