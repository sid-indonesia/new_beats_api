package models

import org.jetbrains.exposed.sql.Table

object Group: Table(){
    val gid = varchar("gid",35).primaryKey()
    val session_id = integer("session_id")
    val uid = varchar("uid", 35)
    val time_start = long("time_start")
    val time_finish = long("time_finish").nullable()
}