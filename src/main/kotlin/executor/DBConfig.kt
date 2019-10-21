package executor

import org.jetbrains.exposed.sql.Database

object DBConfig {
    fun init(){
        val path = System.getProperty("user.dir")
        Database.connect("jdbc:sqlite:${path}/data/beats.db", "org.sqlite.JDBC")
    }
}