# Лабораторная работа №9-10: Продвинутое ООП на Kotlin

## Описание
Лабораторная работа посвящена изучению продвинутых концепций объектно-ориентированного программирования в Kotlin, включая инкапсуляцию, геттеры/сеттеры, data-классы, абстрактные классы и интерфейсы на примере проекта "Galaxy Outpost Manager".

## Структура проекта
Проект содержит примеры реализации продвинутых механизмов ООП, а также пояснения к ключевым концепциям.


src/
├── actions/           # Интерфейсы
├── characters/        # Персонажи и герои
├── example/          # Примеры
├── modules/          # Модули аванпоста
├── resources/        # Ресурсы и управление
└── Main.kt          # Главный файл программы


## Как запустить проект
1. Клонируйте репозиторий:
bash
git clone <https://github.com/Mikailov05/Kotlin_Advanced_Lab_9_10_Mikailov-ISP-231>

2. Откройте проект в IntelliJ IDEA.
3. Запустите любой пример через контекстное меню или напрямую из `main`.

Изученные темы и примеры реализации

1. Геттеры и сеттеры
В Kotlin свойства по умолчанию имеют автоматически сгенерированные геттеры и сеттеры. Их можно переопределять для добавления валидации, логирования или вычисляемых значений.
```
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
```

### 2. Инкапсуляция
Принцип, скрывающий внутреннее состояние объекта и управляющий доступом к нему через модификаторы доступа (`private`, `protected`, `internal`, `public`).

**Пример:**
```kotlin
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
```

### 3. Data-классы
Специальные классы для хранения данных, автоматически генерирующие `toString()`, `equals()`, `hashCode()`, `copy()` и методы декомпозиции.

**Пример:**
```kotlin
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
```

### 4. Абстрактные классы
Классы, которые нельзя инстанцировать напрямую, но можно наследовать. Могут содержать абстрактные и реализованные методы.

**Пример:**
```kotlin
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
```

### 5. Интерфейсы
Контракт поведения, который класс обязуется реализовать. Может содержать функции без реализации, с default-реализацией, а также свойства.

**Пример:**
```kotlin
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
```

## Пример проекта: Galaxy Outpost Manager
Мини-симулятор космической базы, демонстрирующий все изученные концепции:
- **Класс OutpostWorker** — работник с инкапсулированными свойствами энергии и настроения
- **Data-класс OutpostResource** — игровые ресурсы
- **Абстрактный класс OutpostModule** — базовый класс модулей базы
- **Интерфейс ModuleAction** — контракт для взаимодействия с ресурсами
- **Класс ResourceManager** — управление ресурсами

## Автор
[Микаилов Ахмед]

## Лицензия
Проект создан в учебных целях. Свободное использование с указанием авторства.
```

Этот README.md включает:
1. исходное описание
2. Структуру проекта
3. Инструкцию по запуску
4. Подробные разделы по всем изученным темам с кодом
5. Описание учебного проекта
6. Автора и лицензию
