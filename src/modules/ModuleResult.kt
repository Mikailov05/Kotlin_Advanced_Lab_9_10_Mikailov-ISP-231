import jdk.jfr.DataAmount
import javax.print.attribute.standard.JobStateReason

sealed class ModuleResult {
    data class Success(val message: String): ModuleResult()
    data class ResourceProduces(
        val resourceName: String,val amount: Int
    ): ModuleResult()
    data class NotEnoughResources(
        val resourseName: String,
        val requried:Int,
        val available: Int
    ) : ModuleResult()
    data class Error(val reason: String) :ModuleResult()

}