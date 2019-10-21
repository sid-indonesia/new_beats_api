package models

import org.jetbrains.exposed.sql.Table

object Session : Table(){
    val id = integer("id").autoIncrement().primaryKey()
    val uid = varchar("uid", 35)
    val time_start = long("time_start")
    val time_finish = long("time_finish").nullable()
}