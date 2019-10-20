import io.javalin.Javalin
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

fun main(args: Array<String>) {

    Database.connect("jdbc:sqlite:/data/data.db", "org.sqlite.JDBC")

    transaction {
        addLogger(StdOutSqlLogger)

    }

}
