Лабораторная работа №9-10: Продвинутое ООП на Kotlin
Описание
Лабораторная работа посвящена изучению продвинутых концепций объектно-ориентированного программирования в Kotlin, включая инкапсуляцию, геттеры/сеттеры, data-классы, абстрактные классы и интерфейсы на примере проекта "Galaxy Outpost Manager".

Структура проекта
Проект содержит примеры реализации продвинутых механизмов ООП, а также пояснения к ключевым концепциям.

text
src/
├── actions/           # Интерфейсы
├── characters/        # Персонажи и герои
├── example/          # Примеры
├── modules/          # Модули аванпоста
├── resources/        # Ресурсы и управление
└── Main.kt          # Главный файл программы
Как запустить проект
Клонируйте репозиторий:

bash
git clone https://github.com/Mikailov05/Kotlin_Advanced_Lab_9_10_Mikailov-ISP-231.git
Откройте проект в IntelliJ IDEA.

Запустите любой пример через контекстное меню или напрямую из main.

Изученные темы и примеры реализации
1. Геттеры и сеттеры
   В Kotlin свойства по умолчанию имеют автоматически сгенерированные геттеры и сеттеры. Их можно переопределять для добавления валидации, логирования или вычисляемых значений.

Пример:

kotlin
class Hero(val name: String) {
var health: Int = 100
set(value) {
field = value.coerceIn(0,100)
}

    val stamina: Int
        get() = 50 + 10 
}
2. Инкапсуляция
   Принцип, скрывающий внутреннее состояние объекта и управляющий доступом к нему через модификаторы доступа (private, protected, internal, public).

Пример:

kotlin
class OutpostWorker(val name: String) {
var energy: Int = 100
set(value) {
field = value.coerceIn(0..100)
}

    var level: Int = 1
        private set 
    
    fun levelUp() {
        level++ 
    }
}
3. Data-классы
   Специальные классы для хранения данных, автоматически генерирующие toString(), equals(), hashCode(), copy() и методы декомпозиции.

Пример:

kotlin
data class OutpostResource(
val id: Int,
val name: String,
var amount: Int
) {
override fun toString(): String {
return "Ресурс: $name (ID: $id) — $amount ед."
}
}

fun main() {
val resource = OutpostResource(1, "Энергия", 100)
val copy = resource.copy(amount = 150)
println(resource)
}
4. Абстрактные классы
   Классы, которые нельзя инстанцировать напрямую, но можно наследовать. Могут содержать абстрактные и реализованные методы.

Пример:

kotlin
abstract class OutpostModule(val name: String, var level: Int = 1) {
fun upgrade() {
level++
println("$name повышен до уровня $level")
}

    abstract fun performAction(manager: ResourceManager)
}

class EnergyGenerator : OutpostModule("Генератор энергии") {
override fun performAction(manager: ResourceManager) {
println("Генератор производит 20 энергии")
}
}
5. Интерфейсы
   Контракт поведения, который класс обязуется реализовать. Может содержать функции без реализации, с default-реализацией, а также свойства.

Пример:

kotlin
interface VideoPlayable {
fun play()
}

interface AudioPlayable {
fun play()
}

class MediaPlayer : VideoPlayable, AudioPlayable {
override fun play() {
println("Воспроизводится аудио и видео")
}
}

interface Movable {
fun move() {
println("Движение")
}
fun stop()
}

fun main() {
val player = MediaPlayer()
player.play()
}
6. Sealed-классы
   Sealed-классы используются для представления ограниченного набора состояний или результатов, которые известны на этапе компиляции.

Они позволяют:

гарантировать обработку всех возможных вариантов;

безопасно использовать конструкцию when без else;

удобно описывать состояния, события и результаты действий.

Пример: результат работы модуля

kotlin
sealed class ModuleResult {
data class Success(val message: String) : ModuleResult()
data class ResourceProduced(val resourceName: String, val amount: Int) : ModuleResult()
data class NotEnoughResources(
val resourceName: String,
val required: Int,
val available: Int
) : ModuleResult()
data class Error(val reason: String) : ModuleResult()
}
7. Object в Kotlin
   object — это специальная конструкция Kotlin, которая создаёт единственный экземпляр класса (Singleton).

Особенности:

создаётся при первом обращении;

существует в одном экземпляре;

не имеет конструктора.

Пример: глобальный логгер

kotlin
object Logger {
private var counter = 0

    fun log(message: String) {
        counter++
        println("[$counter] $message")
    }
}
Использование:

kotlin
Logger.log("Инициализация системы")
Logger.log("Модуль запущен")
object удобно использовать для:

логгеров;

конфигураций;

состояний без данных в sealed-классах;

утилитарных классов.

8. Делегирование свойств
   Делегирование свойств позволяет передать логику хранения и обработки значения другому объекту. В Kotlin это реализуется с помощью ключевого слова by.

Преимущества:

уменьшение дублирования кода;

централизованная логика проверки и обработки данных;

более чистый и читаемый код.

Пример: ограничение диапазона значения энергии

kotlin
import kotlin.properties.Delegates

class OutpostWorker(val name: String) {
var energy: Int by Delegates.observable(100) { _, old, new ->
println("Энергия изменилась: $old → $new")
}

    var level: Int by Delegates.vetoable(1) { _, _, newValue ->
        newValue in 1..10  // Уровень должен быть между 1 и 10
    }
}
Пример использования:

kotlin
fun main() {
val worker = OutpostWorker("Иван")
worker.energy = 85
// Вывод: Энергия изменилась: 100 → 85

    worker.level = 12  // Не установится, так как 12 > 10
    println("Уровень: ${worker.level}") // Вывод: 1
}
9. Lazy (ленивая инициализация)
   lazy позволяет инициализировать объект только при первом обращении к нему.

Это полезно, если:

объект создаётся не всегда;

его создание ресурсоёмкое;

нужно отложить инициализацию.

Пример:

kotlin
class GalaxyOutpost {
val resourceManager by lazy {
println("Создание ResourceManager...")
ResourceManager()
}

    val heavyDataProcessor by lazy {
        println("Инициализация тяжёлого процессора...")
        HeavyDataProcessor()
    }
}

fun main() {
val outpost = GalaxyOutpost()
println("Аванпост создан")
// ResourceManager ещё не создан

    val manager = outpost.resourceManager
    // Вывод: Создание ResourceManager...
    // Теперь ResourceManager создан и готов к использованию
    
    // HeavyDataProcessor не будет создан, так как мы к нему не обращались
}
Объект ResourceManager будет создан только при первом использовании.

10. Observer-паттерн (наблюдатель)
    Observer-паттерн позволяет объектам реагировать на изменения состояния другого объекта.

В проекте Galaxy Outpost Manager наблюдатели могут:

реагировать на изменение ресурсов;

логировать события;

уведомлять пользователя.

Пример реализации:

kotlin
// Интерфейс наблюдателя
interface ResourceObserver {
fun onResourceChanged(resourceName: String, oldAmount: Int, newAmount: Int)
}

// Класс менеджера ресурсов
class ResourceManager {
private val observers = mutableListOf<ResourceObserver>()
private val resources = mutableMapOf<String, Int>()

    fun addObserver(observer: ResourceObserver) {
        observers.add(observer)
    }
    
    fun removeObserver(observer: ResourceObserver) {
        observers.remove(observer)
    }
    
    fun setResource(name: String, amount: Int) {
        val oldAmount = resources[name] ?: 0
        resources[name] = amount
        
        // Уведомляем всех наблюдателей
        observers.forEach { observer ->
            observer.onResourceChanged(name, oldAmount, amount)
        }
    }
}

// Конкретный наблюдатель
class ConsoleLogger : ResourceObserver {
override fun onResourceChanged(resourceName: String, oldAmount: Int, newAmount: Int) {
println("[ЛОГ] Ресурс '$resourceName' изменился: $oldAmount -> $newAmount")
}
}

// Наблюдатель для уведомлений
class AlertNotifier(private val threshold: Int) : ResourceObserver {
override fun onResourceChanged(resourceName: String, oldAmount: Int, newAmount: Int) {
if (newAmount < threshold) {
println("[ВНИМАНИЕ] Ресурс '$resourceName' ниже порога: $newAmount < $threshold")
}
}
}
Пример использования:

kotlin
fun main() {
val manager = ResourceManager()

    // Добавляем наблюдателей
    manager.addObserver(ConsoleLogger())
    manager.addObserver(AlertNotifier(threshold = 20))
    
    // ResourceManager изменяет ресурсы
    manager.setResource("Энергия", 100)
    // Вывод: [ЛОГ] Ресурс 'Энергия' изменился: 0 -> 100
    
    manager.setResource("Энергия", 15)
    // Вывод:
    // [ЛОГ] Ресурс 'Энергия' изменился: 100 -> 15
    // [ВНИМАНИЕ] Ресурс 'Энергия' ниже порога: 15 < 20
}
Наблюдатель выводит сообщение в консоль при изменении.

11. Сохранение состояния
    Для сохранения состояния проекта используется сериализация в JSON.

Это позволяет:

сохранять данные между запусками программы;

хранить состояние в человекочитаемом формате;

легко перенести логику в Android-приложение.

Пример реализации:

kotlin
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class OutpostState(
val name: String,
val resources: Map<String, Int>,
val modules: List<String>,
val workers: List<String>,
val timestamp: Long = System.currentTimeMillis()
)

class StateManager {
fun saveState(state: OutpostState): String {
return Json.encodeToString(state)
}

    fun loadState(json: String): OutpostState {
        return Json.decodeFromString(json)
    }
}

fun main() {
val stateManager = StateManager()

    // Создаём состояние
    val state = OutpostState(
        name = "Альфа-База",
        resources = mapOf(
            "Энергия" to 150,
            "Вода" to 75,
            "Еда" to 50,
            "Кислород" to 200
        ),
        modules = listOf("Генератор", "Очиститель", "Лаборатория"),
        workers = listOf("Капитан", "Инженер", "Учёный")
    )
    
    // Сохраняем в JSON
    val json = stateManager.saveState(state)
    println("Сохранённое состояние (JSON):")
    println(json)
    
    // Загружаем обратно
    val loadedState = stateManager.loadState(json)
    println("\nЗагруженное состояние:")
    println("Название: ${loadedState.name}")
    println("Ресурсы: ${loadedState.resources}")
    println("Модули: ${loadedState.modules}")
    println("Работники: ${loadedState.workers}")

Пример проекта: Galaxy Outpost Manager
Мини-симулятор космической базы, демонстрирующий все изученные концепции:

Класс OutpostWorker — работник с инкапсулированными свойствами энергии и настроения

Data-класс OutpostResource — игровые ресурсы

Абстрактный класс OutpostModule — базовый класс модулей базы

Интерфейс ModuleAction — контракт для взаимодействия с ресурсами

Sealed-класс ModuleResult — результат работы модулей

Object Logger — глобальный логгер для отслеживания событий

Класс ResourceManager — управление ресурсами

Делегирование свойств — для автоматической валидации данных

Lazy-инициализация — для оптимизации создания объектов

Observer-паттерн — для реактивного управления ресурсами

Сериализация JSON — для сохранения состояния игры

Полный пример использования:
kotlin
fun main() {
// Создаём аванпост
println("=== Galaxy Outpost Manager ===")

    // Используем lazy для создания менеджера ресурсов
    val outpost = GalaxyOutpost()
    
    // Добавляем наблюдателей
    val manager = outpost.resourceManager
    manager.addObserver(ConsoleLogger())
    
    // Используем делегированные свойства
    val worker = OutpostWorker("Алексей")
    worker.energy = 85
    
    // Работа с ресурсами через наблюдателя
    manager.setResource("Энергия", 100)
    manager.setResource("Вода", 50)
    
    // Проверяем уровень
    worker.level = 3
    println("Уровень работника: ${worker.level}")
    
    // Сохраняем состояние
    val state = OutpostState(
        name = "Моя база",
        resources = mapOf("Энергия" to 100, "Вода" to 50),
        modules = listOf("Генератор"),
        workers = listOf("Алексей")
    )
    
    val json = StateManager().saveState(state)
    println("\nСостояние сохранено в JSON (${json.length} символов)")

Автор
[Микаилов Ахмед]

Лицензия
Проект создан в учебных целях. Свободное использование с указанием авторства.