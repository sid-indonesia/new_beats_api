package models

import org.jetbrains.exposed.sql.Table

object Response : Table(){
    val id = integer("id").autoIncrement().primaryKey()
    val uid = (varchar("uid", 35) references Participant.uid)
    val gid = (varchar("gid", 35) references Group.gid).nullable()
    val x = integer("x")
    val y = integer("y")
    val colour = varchar("colour", 10)
    val time_stamp = long("time_stamp")
    val task_id = integer("task_id")
}