package executor

import models.BaseResponse
import models.Participant
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import javax.servlet.http.Part

data class ParticipantData(
    val uid:String,
    val name:String,
    val email:String,
    val gender:String,
    val age:Int,
    val session_id:Int,
    val time_start:Long,
    val time_finish:Long?=null
)

data class ParticipantCallBack(
    val uid:String
)


object ParticipantExecutor:BaseExecutor<ParticipantData, BaseResponse<ParticipantCallBack>>{
    override fun insertData(data: ParticipantData): BaseResponse<ParticipantCallBack> {
        var result : String = ""
        transaction {
            SchemaUtils.create(Participant)
            val participant = Participant.insert {
                it[uid] = data.uid
                it[name] = data.name
                it[email] = data.email
                it[gender] = data.gender
                it[age] = data.age
                it[session_id] = data.session_id
                it[time_start] = data.time_start
                it[time_finish] = data.time_finish
            }get Participant.uid
            result = participant
        }

        return BaseResponse(
            ParticipantCallBack(result),
            200,
            "succes"
        )
    }

    fun selectAll():BaseResponse<List<ParticipantData>>{
        var participants = listOf<ParticipantData>()
        transaction {
            SchemaUtils.create(Participant)
            participants = Participant.selectAll().map {
                ParticipantData(
                    it[Participant.uid],
                    it[Participant.name],
                    it[Participant.email],
                    it[Participant.gender],
                    it[Participant.age],
                    it[Participant.session_id],
                    it[Participant.time_start],
                    it[Participant.time_finish]
                )
            }
        }
        return BaseResponse(
            participants,
            200,
            "succes"
        )
    }
}