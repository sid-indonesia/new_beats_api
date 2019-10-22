package executor

import models.BaseResponse
import models.Response
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

data class ResponseData(
    val id:Int,
    val uid:String,
    val gid:String?=null,
    val x:Int,
    val y:Int,
    val colour:String,
    val task_id:Int,
    val time_stamp:Long
)

object ResponseExecutor : BaseExecutor<ResponseData>{
    override fun insertData(data: ResponseData): BaseResponse<ResponseData> {
        var responseData : ResponseData? = null
        transaction {
            SchemaUtils.create(Response)
            val response = Response.insert {
                it[id]=data.id
                it[uid]=data.uid
                it[gid]=data.gid
                it[x]=data.x
                it[y]=data.y
                it[colour]=data.colour
                it[task_id] = data.task_id
                it[time_stamp]=data.time_stamp
            }get Response.uid
            responseData = select(response)
        }
        return BaseResponse(
            responseData,
            201,
            "succes"
        )
    }

    override fun selectByUID(uid: String): BaseResponse<ResponseData> {
        var response = listOf<ResponseData>()
        transaction {
            SchemaUtils.create(Response)
            response = Response.select{
                Response.id eq uid.toInt()
            }.limit(1).map {
                ResponseData(
                    it[Response.id],
                    it[Response.uid],
                    it[Response.gid],
                    it[Response.x],
                    it[Response.y],
                    it[Response.colour],
                    it[Response.task_id],
                    it[Response.time_stamp])
            }
        }
        if(response.isNotEmpty()){
            return BaseResponse(
                response.firstOrNull(),
                200,
                "succes"
            )
        }else{
            return BaseResponse(
                null,
                404,
                "Data not found"
            )
        }
    }

    override fun select(uid: String): ResponseData? {
        var responseData : ResponseData ? = null
        transaction {
            SchemaUtils.create(Response)
            responseData = Response.select {
                Response.id eq uid.toInt()
            }.map {
                ResponseData(
                    it[Response.id],
                    it[Response.uid],
                    it[Response.gid],
                    it[Response.x],
                    it[Response.y],
                    it[Response.colour],
                    it[Response.task_id],
                    it[Response.time_stamp])
            }.first()
        }
        return responseData
    }

    override fun selectAll(): BaseResponse<List<ResponseData>> {
        var res = listOf<ResponseData>()
        transaction {
            SchemaUtils.create(Response)
            res = Response.selectAll().map {
                ResponseData(
                    it[Response.id],
                    it[Response.uid],
                    it[Response.gid],
                    it[Response.x],
                    it[Response.y],
                    it[Response.colour],
                    it[Response.task_id],
                    it[Response.time_stamp])
            }
        }
        if(res.isNotEmpty()){
            return BaseResponse(
                res,
                200,
                "succes"
            )
        }else{
            return BaseResponse(
                null,
                404,
                "succes"
            )
        }
    }

    override fun updateData(data: ResponseData): BaseResponse<ResponseData> {
        var responseData : ResponseData? = null
        transaction {
            SchemaUtils.create(Response)
            Response.update({
                Response.id eq data.id
            }) {
                it[uid]=data.uid
                it[gid]=data.gid
                it[x]=data.x
                it[y]=data.y
                it[colour]=data.colour
                it[task_id] = data.task_id
                it[time_stamp]=data.time_stamp
            }
            responseData = select(data.uid)
        }
        return BaseResponse(
            responseData,
            201,
            "succes"
        )
    }

    override fun deleteData(uid: String) {
        transaction {
            SchemaUtils.create(Response)
            Response.deleteWhere{Response.uid eq uid}
        }
    }
}