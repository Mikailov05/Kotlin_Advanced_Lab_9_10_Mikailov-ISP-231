import CardType.*

class PaymentValidator{
    fun check(payment: Payment): Boolean{
        return when (payment.type){
            VISA -> payment.card.length ==16
            MASTERCARD -> TODO()
            MIR -> TODO()
            UNKNOWN -> TODO()
        }
    }
}
