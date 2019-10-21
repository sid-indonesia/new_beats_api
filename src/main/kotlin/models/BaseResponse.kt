package models

data class BaseResponse<T>(
    val data:T,
    val code:Int,
    val message:String
)