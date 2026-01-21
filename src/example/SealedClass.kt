package example

sealed class NetworkResult{
    data class Success(val data: String) : NetworkResult()
    data class Error(val message: String, val code: Int) : NetworkResult()
    object  Loading : NetworkResult()
}

fun handleResult(result: NetworkResult) {
    when {
        result is NetworkResult.Success -> {
            println("Успех: ${result.data}")


        }


    }

    fun main() {
        val success = NetworkResult.Success(data = "Данные получены")
        val error = NetworkResult.Error(message = "Сервер не отвечает", code = 500)
        val loading = NetworkResult.Loading

        handleResult(result = success)
        handleResult(result = error)
        handleResult(result = loading)


    }
}




