import kotlin.properties.Delegates

object SystemLogger {
    init {
        println("SystemLogger инициализирован")
    }

    fun log(message: String) {
        println("[LOG] $message")
    }
}

val logger by lazy {
    SystemLogger
}

fun main(){
    val processor = PaymentProcessor()
    val payments = listOf(
        Payment(card = "4_111_111_111_111_111", sum = 1000, type = CardType.VISA),
        Payment(card = "5_111_111_111_111_111", sum = 2000, type = CardType.MASTERCARD),
        Payment(card = "2_222_222_222_222_222", sum = 1500, type = CardType.MIR),
        Payment(card = "1234567812345678", sum = 500, type = CardType.UNKNOWN),
        Payment(card = "123", sum = 1000, type = CardType.VISA)



    )
    println("=== Обработка платежей ===")
    payments.forEach { payment ->
        println("\n Платеж ${payment.type}: ${payment.card.take(n=4)}...${payment.sum} руб")
        //  val result = processor.pay(payment)
        //  processor.show(result)
    }
    println("\n=== Сравнение data class ===")
    //  println("Платеж 1: $payment1")
    // println("Платеж 2: $payment2")
    // println("Одинаковые? ${payment1==payment2}")

    logger.log("Запуск базы")

}