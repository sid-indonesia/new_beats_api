package models

import org.jetbrains.exposed.sql.Table

object Participant : Table(){
    val uid = varchar("uid", 35).primaryKey()
    val name = varchar("name", 35)
    val email = varchar("email", 35)
    val gender = varchar("gender", 3)
    val age = integer("age")
    val session_id = (varchar("session_id", 35) references Session.uid)
    val time_start = long("time_start")
    val time_finish = long("time_finish").nullable()
}