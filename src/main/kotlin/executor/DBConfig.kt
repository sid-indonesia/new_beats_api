package executor

import org.jetbrains.exposed.sql.Database

object DBConfig {
    val db by lazy {
        Database.connect("jdbc:sqlite:/data/data.db", "org.sqlite.JDBC")
    }
}