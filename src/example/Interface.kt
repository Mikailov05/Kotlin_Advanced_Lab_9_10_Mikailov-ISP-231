package example

interface Movable {
var speed: Int
val model: String
val number: String
    fun move()


    class Car(model: String, number: String) : Movable {
        var speed = 60
        override fun move() {

        println("Едем на машине сщ скоростью $speed км/ч")
        }


    }


    interface Movable {
        fun move()
        fun stop(){
            println("Останавливаемся")
        }
    }
    class Aitcraft(model: String, number: String) : Movable{
        var speed = 600
        override  fun move(){
            println("Летим на самолете")
        }

        override fun stop() {


        }
    }

    fun main(){
        val car = Car(model = "LADA", number = "05LAD")
        val aitcraft = Aitcraft(model = "Boeing", number = "737")
        travel(obj = car)
        travel(obj = aitcraft)
    }
    fun travel(obj: Movable) = obj.move()
}

interface Worker{
    fun work()
}
interface Student {
    fun study()

    class WorkingStudent(val name: String) : Worker, Student {
        override fun work() = println("$name Работает")
        override fun study() = println("$name Учится")
    }

    interface VideoPlayable {
        fun play() = println("Play video")
        interface AudioPlayable {
            fun play()= println("Play audio")
        }

        class MediaPlayer : VideoPlayable, AudioPlayable{
            override  fun play() = println("Play audio and video")
        }
        fun main(){
            val player = MediaPlayer()
            player.play()
        }
    }
    }