import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform

fun main() {
    println(rotateImage())
}

inline fun <reified T1, T2> Flow<State<T1>>.chainWith(
    crossinline transform: suspend (T1) -> Flow<State<T2>>
): Flow<State<Pair<T1, State<T2>>>> = transform { state ->
    when (state) {
        is State.Loading -> emit(State.Loading)

        is State.Failed -> {
            emit(State.Failed(state.failedMessage))
            return@transform
        }

        is State.Success -> {
            transform.invoke(state.data).collect { state2 ->
                when (state2) {
                    is State.Loading -> emit(State.Loading)

                    is State.Failed -> {
                        emit(State.Success(Pair(state.data, state2)))
                    }

                    is State.Success -> {
                        emit(State.Success(Pair(state.data, state2)))
                    }
                }
            }
        }
    }
}

fun <T, R> Flow<Pair<State<T>, State<R>>>.asStatePair(): Flow<State<Pair<T, R>>> = transform { state ->
    val stateFirst = state.first
    val stateSecond = state.second
    var stateFirstData: T? = null
    var stateSecondData: R? = null

    when (stateFirst) {
        is State.Loading -> emit(State.Loading)
        is State.Failed -> {
            emit(State.Failed(stateFirst.failedMessage))
            return@transform
        }

        is State.Success -> {
            stateFirstData = stateFirst.data
        }
    }

    when (stateSecond) {
        is State.Loading -> emit(State.Loading)
        is State.Failed -> {
            emit(State.Failed(stateSecond.failedMessage))
            return@transform
        }

        is State.Success -> {
            stateSecondData = stateSecond.data
        }
    }

    if (stateFirstData != null && stateSecondData != null) {
        emit(State.Success(Pair(stateFirstData, stateSecondData)))
    }
}

fun <T> State<T>.manageState(onSuccess: (T) -> Unit) {
    when (this) {
        is State.Loading -> println("Loading")
        is State.Success -> onSuccess.invoke(data)
        is State.Failed -> println(failedMessage)
    }
}

fun doLongRunningTaskOne(param: Int): Flow<State<Int>> {
    return flow {
        emit(State.Loading)
        delay(1000)
        throw IllegalStateException("test")
        emit(State.Success(param))
//        emit(State.Failed("failed1"))
    }
}

fun doLongRunningTaskTwo(param: String): Flow<State<String>> {
    return flow {
        emit(State.Loading)
        delay(1000)
//        emit(State.Success("ResultTwo: $param"))
        emit(State.Failed("failed2: $param"))
    }
}

fun doLongRunningTaskThree(): Flow<State<String>> {
    return flow {
        emit(State.Loading)
        delay(1500)
        emit(State.Success("ResultThree"))
    }
}

sealed interface State<out T> {
    data class Success<out R>(val data: R) : State<R>
    data class Failed<out R>(val failedMessage: String) : State<R>
    object Loading : State<Nothing>
}
