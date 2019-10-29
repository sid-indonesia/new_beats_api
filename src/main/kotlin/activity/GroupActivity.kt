package activity

import executor.GroupData
import executor.GroupExecutor
import io.javalin.Javalin

fun GroupActivity(app: Javalin){
    app.post("/groups"){call->
        val data = call.bodyAsClass(GroupData::class.java)
        val response = GroupExecutor.insertData(data)
        responseCallBack(call, response)
    }

    app.get("/groups"){call->
        val response = GroupExecutor.selectAll()
        responseCallBack(call, response)
    }

    app.get("/groups/:uid"){call->
        val response = GroupExecutor.selectByUID(call.pathParam(":uid"))
        responseCallBack(call, response)
    }

    app.put("/groups/:uid"){call->
        val data = call.bodyAsClass(GroupData::class.java)
        val response = GroupExecutor.updateData(data)
        responseCallBack(call, response)
    }

    app.delete("/groups/:uid"){call->
        GroupExecutor.deleteData(call.pathParam(":uid"))
        call.status(204)
    }
}