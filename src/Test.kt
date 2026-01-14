var age: Int = 18
    get() = field
    set(value){
        if((value > 0 ) and (value < 110))
            field = value
    }

fun main(){
    println(age) //18
    age = 45
    println(age) //45
    age = -225
    println(age) //45


}