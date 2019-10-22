package executor

import models.BaseResponse

interface BaseExecutor<T> {
    fun insertData(data:T):BaseResponse<T>
    fun selectByUID(uid:String):BaseResponse<T>
    fun selectAll():BaseResponse<List<T>>
    fun select(uid:String):T?
    fun updateData(data:T):BaseResponse<T>
    fun deleteData(uid:String)
}