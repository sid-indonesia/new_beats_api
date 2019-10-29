package activity

import executor.ResponseData
import executor.ResponseExecutor
import io.javalin.Javalin

fun responseActivity(app: Javalin){
    app.post("/responses"){call->
        val data = call.bodyAsClass(ResponseData::class.java)
        val response = ResponseExecutor.insertData(data)
        responseCallBack(call, response)
    }

    app.get("/responses"){call ->
        val response = ResponseExecutor.selectAll()
        responseCallBack(call, response)
    }

    app.get("/responses/:uid"){call ->
        print(call.pathParam(":uid"))
        val response = ResponseExecutor.selectByUID(call.pathParam(":uid"))
        responseCallBack(call, response)
    }

    app.put("/responses/:uid"){call->
        val data = call.bodyAsClass(ResponseData::class.java)
        val response = ResponseExecutor.updateData(data)
        responseCallBack(call, response)
    }

    app.delete("/responses/:uid"){call->
        ResponseExecutor.deleteData(call.pathParam(":uid"))
        call.status(204)
    }
}