package activity

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.javalin.http.Context
import models.BaseResponse


fun <T>jsonMapper(data:T):String{
    val mapper = jacksonObjectMapper()
    return mapper.writeValueAsString(data)
}

fun <T>responseCallBack(ctx:Context, data:BaseResponse<T>){
    ctx.result(jsonMapper(data)).contentType("application/json").status(data.code)
}