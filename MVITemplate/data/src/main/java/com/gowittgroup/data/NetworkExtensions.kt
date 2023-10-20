package com.gowittgroup.data

import com.gowittgroup.data.models.CallErrors
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retryWhen
import java.io.IOException
import com.gowittgroup.data.models.Result
import kotlinx.coroutines.flow.catch

fun <T : Any> Flow<Result<T>>.applyCommonSideEffects() = retryWhen { cause, attempt ->
    when {
        (cause is IOException && attempt < Utils.MAX_RETRIES) -> {
            delay(Utils.getBackoffDelay(attempt))
            true
        }
        else -> {
            false
        }
    }
}.onStart {
    emit(Result.Loading)
}.catch {
    emit(Result.Error(CallErrors.ErrorException(it)))
}
