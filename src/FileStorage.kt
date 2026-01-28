import resources.OutpostResource
import java.io.File

object FileStorage {
    private const val FILE_NAME = "outpost_state.txt"

    fun save(resources: List<OutpostResource>) {
        val file = File(pathname = FILE_NAME)
        file.writeText(
            text = resources.joinToString(separator = "\n") {
                "${it.id.toString().padEnd(length = 3)} | ${it.name.padEnd(length = 10)} | ${it.amount}"
            }
        )
        println("Состояние базы сохранено в файл")
    }

    fun load(): List<OutpostResource> {
        println("Загрузка состояния базы из файла")
        val file = File(FILE_NAME)
        return file.readLines().map {
            val (id, name, amount) = it.split(delimiters = ";")
            OutpostResource(id.toInt(), name, amountInit = amount.toInt())
        }
    }

    fun getAll(): List<OutpostResource> = resources.toList()
}