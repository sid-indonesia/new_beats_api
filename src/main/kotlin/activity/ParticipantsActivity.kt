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

    app.put("/participants/:uid"){call ->
        val data = call.bodyAsClass(ParticipantData::class.java)
        val response = ParticipantExecutor.updateData(data)
        responseCallBack(call, response)
    }

    app.delete("/participants/:uid"){call->
        ParticipantExecutor.deleteData(call.pathParam(":uid"))
        call.status(204)
    }

}