package executor

import models.BaseResponse
import models.Group
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

data class GroupData(
    val uid: String,
    val gid: String,
    val session_id:Int,
    val time_start:Long,
    val time_finish:Long?=null
)

object GroupExecutor:BaseExecutor<GroupData>{
    override fun selectByUID(uid: String): BaseResponse<GroupData> {
        val group = select(uid)
        return BaseResponse(
            group,
            200,
            "success"
        )
    }

    override fun selectAll(): BaseResponse<List<GroupData>> {
        var groups = listOf<GroupData>()
        transaction {
            SchemaUtils.create(Group)
            groups = Group.selectAll().map {
                GroupData(
                    it[Group.gid],
                    it[Group.uid],
                    it[Group.session_id],
                    it[Group.time_start],
                    it[Group.time_finish]
                )
            }
        }

        if (groups.isEmpty()){
            return BaseResponse(
                null,
                404,
                "success"
            )
        } else{
            return BaseResponse(
                groups,
                200,
                "success"
            )
        }
    }

    override fun select(uid: String): GroupData? {
        var groupData : GroupData? = null
        print(uid)
        transaction {
            SchemaUtils.create(Group)
            groupData = Group.select {
                Group.gid eq uid
            }.map {
                GroupData(
                    it[Group.uid],
                    it[Group.gid],
                    it[Group.session_id],
                    it[Group.time_start],
                    it[Group.time_finish]
                )
            }.first()
        }
        return groupData
    }

    override fun updateData(data: GroupData): BaseResponse<GroupData> {
        var result: GroupData? = null
        transaction {
            SchemaUtils.create(Group)
            Group.update({
                Group.gid eq data.gid
            }) {
                it[uid] = data.uid
                it[session_id] = data.session_id
                it[time_start] = data.time_start
                it[time_finish] = data.time_finish
            }
            result = select(data.gid)
        }

        return BaseResponse(
            result,
            201,
            "success"
        )
    }

    override fun deleteData(uid: String) {
        transaction {
            SchemaUtils.create(Group)
            Group.deleteWhere {
                Group.gid eq uid
            }
        }
    }

    override fun insertData(data: GroupData): BaseResponse<GroupData> {
        var result: GroupData? = null
        transaction {
            SchemaUtils.create(Group)
            val groups = Group.insert {
                it[uid] = data.uid
                it[gid] = data.gid
                it[session_id] = data.session_id
                it[time_start] = data.time_start
                it[time_finish] = data.time_finish
            } get Group.gid

            result = select(groups)
        }

        return BaseResponse(
            result,
            201,
            "success"
        )
    }

}