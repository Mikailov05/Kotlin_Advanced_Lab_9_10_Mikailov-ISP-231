import kotlin.math.abs

var age: Int = 18
    get() = field
    set(value){
        if((value > 0 ) and (value < 110))
            field = value
    }

data class  Item(
    val id: Int,
    val name: String,
    val quantity: Int
) {
    override fun toString(): String {
        return "ID: $id | Имя: $name Кол-во: $quantity "
    }
}

fun main() {
    println(age) //18
    age = 45
    println(age) //45
    age = -225
    println(age) //45
    val sword = Item(id = 1, name = "Sword", quantity = 1)
    val betterSword = sword.copy(quantity = 2)
    println(sword.toString())
    println(betterSword.toString())


    abstract class Human(val name: String) {
        abstract var age: Int

        abstract fun hello()
    }

    fun main() {
        val denis: Human
        //  val pavel: Human = Human(name = "Pavel")
    }

    class Person(name: String) : Human(name) {
        override var age: Int = 1
        override fun hello() {
            //}
            //  val denis: Person = Person(name = "Denis")
//    val maksim: Human = Person(name = "Maksim")
            //  denis.hello()
            //maksim.hello()
        }
    }

    abstract class Figure {
        abstract fun perimetr(): Float
        abstract fun area(): Float
    }
    // class Rectangle(val width: Float,val height: Float) : Figure(){
    // override fun perimetr(): Float

    //  }
//}
}