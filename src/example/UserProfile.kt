import kotlin.properties.Delegates

class UserProfile(initialName: String,inititalEmail: String){}

var name: String by Delegates.observable(initialValue = initialName){
    _, old, new ->
    println("Имя изменено: '$old' -> '$new'")

    var email: String by Delegates.observable(initialValue = initialEmail){
        _, old, new ->
        println("Email обнавлен: '$old' -> '$new;")
    }
    val avatar: String by lazy{
        println("Загружен аватар для $name...")
        "avatar_of_$name.png"
    }
}
fun main() {
    println("Создаём профиль пользователя...")
    val user = UserProfile(initialName = "Алиса", initialEmail = "alice@example.com")

    println("\nИмя: ${user.name}")
    println("Email: ${user.email}")

    println("\nОбращаемся к аватару впервые:")
    println("Файл аватара: ${user.avatar}")

    println("\nОбращаемся к аватару снова (должен быть взят из кэша):")
    println("Файл аватара: ${user.avatar}")

    println("\nМеняем email:")
    user.email = "alice_new@example.org"

    println("\nМеняем имя:")
    user.name = "Алиса К."
}