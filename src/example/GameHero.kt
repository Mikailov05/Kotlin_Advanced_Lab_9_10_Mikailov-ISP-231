import kotlin.properties.Delegates

class UserProfile(initialName: String, initialEmail: String) {
    var name by Delegates.observable(initialName) { _, old, new ->
        println("Имя изменено с '$old' на '$new'")
    }

    var email by Delegates.observable(initialEmail) { _, old, new ->
        println("Email изменён с '$old' на '$new'")
    }

    val avatar by lazy {
        println("Загружаю аватар с сервера...")
        "/avatars/${email.substringBefore('@')}.png"
    }
}

class GameHero(startName: String) {
    var name: String by Delegates.observable(initialValue = startName) {
            _, old, new ->
        println("[$old] получил новое имя: $new!")
    }

    var mana: Int by Delegates.observable(initialValue = 100) {
            _, old, new ->
        println("Мана: $old → $new")
    }

    val ultimate: String by lazy {
        println("Загружаем анимацию способности...")
        "Метеоритный дождь"
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

    println("\n\nСоздаём героя...")
    val hero = GameHero(startName = "Воин Света")

    println("\nТекущая мана: ${hero.mana}")
    println("\nГерой пытается использовать способность:")
    println("Способность: ${hero.ultimate}")
    println("\nПовторный вызов способности (должна быть мгновенной):")
    println("Способность: ${hero.ultimate}")
    println("\nМеняем имя героя:")
    hero.name = "Тёмный Паладин"

    println("\nГерой восстанавливает ману:")
    hero.mana = 200
}