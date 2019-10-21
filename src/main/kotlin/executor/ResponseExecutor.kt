package executor

import models.BaseResponse
import models.Response
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

data class ResponseData(
    val uid:String,
    val gid:String?=null,
    val x:Int,
    val y:Int,
    val colour:String,
    val task_id:Int,
    val time_stamp:Long
)

data class ResponseCallBack(
    val uid:String
)

object ResponseExecutor : BaseExecutor<ResponseData, BaseResponse<ResponseCallBack>>{
    override fun insertData(data: ResponseData): BaseResponse<ResponseCallBack> {
        var result:String = ""
        transaction {
            SchemaUtils.create(Response)
            val response = Response.insert {
                it[uid]=data.uid
                it[gid]=data.gid
                it[x]=data.x
                it[y]=data.y
                it[colour]=data.colour
                it[task_id] = data.task_id
                it[time_stamp]=data.time_stamp
            }get Response.uid
            result = response
        }
        return BaseResponse(
            ResponseCallBack(result),
            200,
            "succes"
        )
    }
}