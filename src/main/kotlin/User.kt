import java.util.concurrent.atomic.AtomicInteger

data class User(val neme: String, val email: String, val id: Int)

class UserDao{
    val users = hashMapOf(
        0 to User("Jihad","jihad@gmail.com",1),
        1 to User("awawa","aw@gmail.com",2)
    )

    var lastId: AtomicInteger = AtomicInteger(users.size -1)
}