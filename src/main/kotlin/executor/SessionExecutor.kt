package executor

import models.BaseResponse
import models.Session
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

data class Sessions(
    var id:Int?=null,
    var uid:String,
    var time_start:Long,
    var time_finish:Long?=null
)

object SessionExecutor : BaseExecutor<Sessions,BaseResponse<Int>>{

    override fun insertData(data: Sessions): BaseResponse<Int> {
        var result :Int = 0
        transaction {
            SchemaUtils.create(Session)
            val session = Session.insert {
                it[uid] = data.uid
                it[time_start] = data.time_start
            }get Session.id
            result = session
        }
        val baseResponse = BaseResponse(
            result,
            200,
            "succes"
        )
        return baseResponse
    }

    fun selectAll():BaseResponse<List<Sessions>>{
        var sessions = listOf<Sessions>()
        transaction {
            SchemaUtils.create(Session)
            sessions = Session.selectAll().map {
                Sessions(
                    it[Session.id],
                    it[Session.uid],
                    it[Session.time_start],
                    it[Session.time_finish]
                )
            }
        }
        return BaseResponse(
            sessions,
            200,
            "succes"
        )

    }
}