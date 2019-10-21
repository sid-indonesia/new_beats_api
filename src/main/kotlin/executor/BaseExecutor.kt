package executor

interface BaseExecutor<T,G> {
    fun insertData(data:T):G
//    fun <T>selectData():T
//    fun deleteData()
//    fun updateData()
}