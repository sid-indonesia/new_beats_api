package executor

import models.BaseResponse
import models.Participant
import models.Response
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import javax.servlet.http.Part

data class ParticipantData(
    val uid:String,
    val name:String,
    val email:String,
    val gender:String,
    val age:Int,
    val session_id:String,
    val time_start:Long,
    val time_finish:Long?=null
)

object ParticipantExecutor:BaseExecutor<ParticipantData>{
    override fun selectByUID(uid: String): BaseResponse<ParticipantData> {
        val participant = select(uid)
        return BaseResponse(
            participant,
            200,
            "OK"
        )
    }

    override fun select(uid: String): ParticipantData? {
        var participantData : ParticipantData ? = null
        transaction {
            SchemaUtils.create(Participant)
            participantData = Participant.select {
                Participant.uid eq uid
            }.map {
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
            }.first()
        }
        return participantData
    }

    override fun selectAll(): BaseResponse<List<ParticipantData>> {
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
        if(participants.isNotEmpty()){
            return BaseResponse(
                participants,
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

    override fun insertData(data: ParticipantData): BaseResponse<ParticipantData> {
        var result : ParticipantData?=null
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
            } get Participant.uid
            result = select(participant)
        }
        return BaseResponse(
            result,
            201,
            "succes"
        )
    }

    override fun updateData(data: ParticipantData): BaseResponse<ParticipantData> {
        var result : ParticipantData?=null
        transaction {
            SchemaUtils.create(Participant)
            val participant = Participant.update({
                Participant.uid eq data.uid
            }) {
                it[name] = data.name
                it[email] = data.email
                it[gender] = data.gender
                it[age] = data.age
                it[session_id] = data.session_id
                it[time_start] = data.time_start
                it[time_finish] = data.time_finish
            }
            result = select(data.uid)
        }
        return BaseResponse(
            result,
            201,
            "succes"
        )
    }

    override fun deleteData(uid: String){
        transaction {
            SchemaUtils.create(Participant)
            Participant.deleteWhere { Participant.uid eq uid }
        }
    }

}