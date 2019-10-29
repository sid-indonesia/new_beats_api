import activity.GroupActivity
import activity.participantsActivity
import activity.responseActivity
import activity.sessionsActivity
import io.javalin.Javalin
import models.Participant
import models.Response
import models.Session
import org.eclipse.jetty.util.log.Log
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import com.fasterxml.jackson.core.*
import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import executor.*

fun main(args: Array<String>){
    DBConfig.init()
    val mapper = jacksonObjectMapper()
    val app = Javalin.create().start(8000)
    app.get("/"){ ctx -> ctx.result("Beats Api Service V1")}

//    Sessions activity here
    sessionsActivity(app)
//    Response Activity here
    responseActivity(app)
//    Participants activity here
    participantsActivity(app)
//    Groups activity here
    GroupActivity(app)
}
