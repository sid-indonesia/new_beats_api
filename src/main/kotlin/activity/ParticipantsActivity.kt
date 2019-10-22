package activity

import executor.ParticipantData
import executor.ParticipantExecutor
import io.javalin.Javalin

fun participantsActivity(app: Javalin){
    app.post("/participants"){call ->
        val data = call.bodyAsClass(ParticipantData::class.java)
        val response = ParticipantExecutor.insertData(data)
        responseCallBack(call, response)
    }

    app.get("/participants"){call->
        val response = ParticipantExecutor.selectAll()
        responseCallBack(call, response)
    }

    app.get("/participants/:uid"){call ->
        val response = ParticipantExecutor.selectByUID(call.pathParam(":uid"))
        responseCallBack(call, response)
    }
}