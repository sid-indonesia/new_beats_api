package activity

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

fun <T>jsonMapper(data:T):String{
    val mapper = jacksonObjectMapper()
    return mapper.writeValueAsString(data)
}