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

fun main(){
    println(age) //18
    age = 45
    println(age) //45
    age = -225
    println(age) //45
val sword = Item(id = 1, name = "Sword", quantity = 1)
    val betterSword = sword.copy(quantity = 2)
    println(sword.toString())
    println(betterSword.toString())
}