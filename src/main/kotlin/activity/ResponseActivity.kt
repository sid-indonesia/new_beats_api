package activity

import executor.ResponseData
import executor.ResponseExecutor
import io.javalin.Javalin

fun responseActivity(app: Javalin){
    app.post("/responses"){call->
        val data = call.bodyAsClass(ResponseData::class.java)
        val response = jsonMapper(ResponseExecutor.insertData(data))
        call.result(response).contentType("application/json")
    }

    app.get("/responses"){call ->
        call.result(jsonMapper(ResponseExecutor.selectAll())).contentType("application/json")
    }

    app.get("/response/?uid=uid"){
        print(it.pathParam("uid"))
    }
}