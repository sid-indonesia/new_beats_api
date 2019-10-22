package models

data class BaseResponse<T>(
    val data:T?=null,
    val code:Int,
    val message:String
)