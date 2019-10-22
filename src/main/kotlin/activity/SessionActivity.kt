package activity

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import executor.SessionExecutor
import executor.Sessions
import io.javalin.Javalin

fun sessionsActivity(app:Javalin){
    app.post("/sessions"){call ->
        val data = call.bodyAsClass(Sessions::class.java)
        val response = SessionExecutor.insertData(data)
        responseCallBack(call,response)
    }

    app.get("/sessions"){ call ->
        val response = SessionExecutor.selectAll()
        responseCallBack(call, response)
    }

    app.get("/sessions/:uid"){call ->
        val response = SessionExecutor.selectByUID(call.pathParam(":uid"))
        responseCallBack(call, response)
    }

    app.delete("/sessions/:uid"){call ->
        SessionExecutor.deleteData(call.pathParam(":uid"))
        call.status(204)
    }

    app.put("/sessions/:uid"){call ->
        val data = call.bodyAsClass(Sessions::class.java)
        val response = SessionExecutor.updateData(data)
        responseCallBack(call, response)
    }
}