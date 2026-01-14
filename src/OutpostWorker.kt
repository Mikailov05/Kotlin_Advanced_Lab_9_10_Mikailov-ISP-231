class OutpostWorker(val name: String) {
    var energy: Int = 100
        set(value) {
            field = value.coerceIn(0, 100)
        }


    var mood: Int = 50
        get() = field + (energy / 10)


    var level: Int = 1
        private set

    fun work() {
        println("name выполняет рвботу")
        energy -= 15
        if (energy < 20) println("$name устал")
    }

    fun rest() {
        println("$name отдыхает")
        energy += 20
    }


    fun levelUp() {

        level++
        println("$name повышает уровень ")
    }

    var maxEnergy: Int = 100
        private set
}

fun main(){
    val worker = OutpostWorker(name = "ахмед")
    println("Энергия: ${worker.energy}")
    println("Настроение: ${worker.mood}")
    worker.work()
    worker.work()
    worker.work()
    worker.work()
    worker.work()
    worker.work()
    worker.work()
    println("Энергия: ${worker.energy}")
    println("Настроение: ${worker.mood}")
    worker.energy = 150
//    worker.level = 5

    worker.levelUp()


}