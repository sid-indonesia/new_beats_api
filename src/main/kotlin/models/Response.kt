package models

import org.jetbrains.exposed.sql.Table

object Response : Table(){
    val uid = varchar("uid", 35).primaryKey()
    val gid = varchar("gid", 35).nullable()
    val x = integer("x")
    val y = integer("x")
    val colour = varchar("colour", 10)
    val time_stamp = long("time_stamp")
    val task_id = integer("task_id")
}