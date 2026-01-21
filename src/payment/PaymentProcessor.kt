class PaymentProcessor {
    private val validator = PaymentValidator()
}


fun pay(payment: Payment): PaymentResult {
    val validator = null
//    if (!validator.check(payment))
        return PaymentResult.Error(reason = "Ошибка валидации")

}

fun show(result: PaymentResult){
    when(result){
        is PaymentResult.Success -> println("Успех: ${result.id}")
        is PaymentResult.Error -> println("Успех: ${result.reason}")

        PaymentResult.Procssing -> println("В обработке...")
    }
}