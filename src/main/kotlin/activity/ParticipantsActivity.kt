package activity

import executor.ParticipantData
import executor.ParticipantExecutor
import io.javalin.Javalin

fun participantsActivity(app: Javalin){
    app.post("/participants"){call ->
        val data = call.bodyAsClass(ParticipantData::class.java)
        val response = jsonMapper(ParticipantExecutor.insertData(data))
        call.result(response).contentType("application/json")
    }

    app.get("/participants"){call->
        call.result(jsonMapper(ParticipantExecutor.selectAll())).contentType("application/json")
    }
}