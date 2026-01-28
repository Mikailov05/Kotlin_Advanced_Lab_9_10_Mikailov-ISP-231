import kotlin.properties.Delegates

data class OutpostResource(
    val id: Int,
    val name: String,
    val amountInit: Int
) {
    var amount: Int by Delegates.observable(initialValue = amountInit) { _, old, new ->
        println("Ресурс [$name] изменился: $old → $new")
    }
}

fun main() {
    val gas = OutpostResource(id = 1, name = "Gas", amountInit = 100)
    val mineral = OutpostResource(id = 2, name = "Minerals", amountInit = 250)
    println("Успех! Вы добыли дополнительное количество минералов: ${mineral.amount + 50}")
    val bonusMineral = mineral.copy(id = 3, name = "Minerals Bonus", amountInit = mineral.amount + 50)
    println(gas.toString())
    println(mineral.toString())
    println(bonusMineral.toString())
}