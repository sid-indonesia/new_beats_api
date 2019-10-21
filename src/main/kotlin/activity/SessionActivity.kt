package activity

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import executor.SessionExecutor
import executor.Sessions
import io.javalin.Javalin

fun sessionsActivity(app:Javalin){
    app.post("/sessions"){call ->
        val data = call.bodyAsClass(Sessions::class.java)
        val response = jsonMapper(SessionExecutor.insertData(data))
        call.result(response).contentType("application/json")
    }

    app.get("/sessions"){ call ->
        call.result(jsonMapper(SessionExecutor.selectAll())).contentType("application/json")
    }
}