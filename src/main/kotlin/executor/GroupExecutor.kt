package executor

import models.BaseResponse
import models.Group
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

data class GroupData(
    val uid: String,
    val gid: String,
    val session_id:Int,
    val time_start:Long,
    val time_finish:Long?=null
)

data class GroupCallback(
    val gid: String
)

object GroupExecutor:BaseExecutor<GroupData, BaseResponse<GroupCallback>>{
    override fun insertData(data: GroupData): BaseResponse<GroupCallback> {
        var result: String = ""
        transaction {
            SchemaUtils.create(Group)
            val groups = Group.insert {
                it[uid] = data.uid
                it[gid] = data.gid
                it[session_id] = data.session_id
                it[time_start] = data.time_start
                it[time_finish] = data.time_finish
            } get Group.gid

            result = groups
        }

        return BaseResponse(
            GroupCallback(result),
            200,
            "succes"
        )
    }

}