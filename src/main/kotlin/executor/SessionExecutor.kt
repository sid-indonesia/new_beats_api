package executor

import models.BaseResponse
import models.Session
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

data class Sessions(
    var id:Int?=null,
    var uid:String,
    var time_start:Long,
    var time_finish:Long?=null
)

object SessionExecutor : BaseExecutor<Sessions>{
    override fun insertData(data: Sessions): BaseResponse<Sessions> {
        var sessions : Sessions ? = null
        transaction {
            SchemaUtils.create(Session)
            val session = Session.insert {
                it[uid] = data.uid
                it[time_start] = data.time_start
            }get Session.uid
            sessions = select(session)
        }
        return BaseResponse(
            sessions,
            201,
            "succes"
        )
    }

    override fun selectByUID(uid: String): BaseResponse<Sessions> {
        var sessions =  select(uid)
        return BaseResponse(
            sessions,
            200,
            "succes"
        )
    }

    override fun selectAll(): BaseResponse<List<Sessions>> {
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

    override fun select(uid: String): Sessions? {
       var sessions : Sessions ? = null
       transaction {
           SchemaUtils.create(Session)
           sessions = Session.select {
               Session.uid eq uid
           }.map {
               Sessions(
                   it[Session.id],
                   it[Session.uid],
                   it[Session.time_start],
                   it[Session.time_finish]
               )
           }.firstOrNull()
       }
        return sessions
    }

    override fun updateData(data: Sessions): BaseResponse<Sessions> {
        var sessions : Sessions ? = null
        transaction {
            SchemaUtils.create(Session)
            val session = Session.update({
                Session.uid eq data.uid
            }) {
                it[time_start] = data.time_start
                it[time_finish] = data.time_finish
            }
            sessions = select(data.uid)
        }
        return BaseResponse(
            sessions,
            201,
            "succes"
        )
    }

    override fun deleteData(uid: String) {
        transaction {
            SchemaUtils.create(Session)
            Session.deleteWhere { Session.uid eq uid }
        }
    }
}