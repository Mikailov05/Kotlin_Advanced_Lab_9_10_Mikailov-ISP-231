import kotlin.properties.Delegates

interface Base {
    fun someFun()
}

class BaseImpl : Base {
    override fun someFun() {

    }
}

class Derived(someBase: Base) : Base by someBase

interface Messenger {
    fun sendTextMessage()
    fun sendVideoMessage()
}

class InstantMessenger(val programName: String) : Messenger {
    override fun sendTextMessage() = println("Send text message")
    override fun sendVideoMessage() = println("Send video message")
}

interface PhotoDevice {
    fun takePhoto()
}

class PhotoCamera : PhotoDevice {
    override fun takePhoto() = println("Take a photo")
}

class SmartPhone(val name: String, m: Messenger, p: PhotoDevice) : Messenger by m, PhotoDevice by p

var counter: Int by Delegates.observable(initialValue = 0) { _, old, new ->
    println("Счетчик изменился: $old -> $new")
}

class User {
    var name: String by Delegates.observable(initialValue = "<no name>") { _, old, new ->
        println("Имя изменено: '$old' -> '$new'")
    }
}

fun main() {
    println("Текущее значение counter: $counter")
    counter = 1
    counter = 5

    val max = InstantMessenger(programName = "MAX")
    val photoCamera = PhotoCamera()
    val yotaPhone = SmartPhone(name = "YotaPhone", m = max, p = photoCamera)  // добавлен параметр p
    yotaPhone.sendTextMessage()
    yotaPhone.sendVideoMessage()
    yotaPhone.takePhoto()

   class User{}
}