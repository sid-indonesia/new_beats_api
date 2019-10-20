package executor

import models.Session
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

data class Sessions(var uid:String, var time_start:Long)

object SessionExecutor {
    fun createSession(session_in: Sessions){
        transaction {
            SchemaUtils.create(Session)
            val session = Session.insert {
                it[uid] = session_in.uid
                it[time_start] =session_in.time_start
            }get Session.id
            Session.selectAll().forEach {
                println(it)
            }
//            println("Session: ${Session.selectAll()}")
        }
    }
}