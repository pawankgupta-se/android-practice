package com.gowittgroup.commonui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gowittgroup.commonui.R
import com.gowittgroup.data.models.CallErrors
import kotlin.reflect.KClass

fun <T: ViewModel> RootBaseActivity.viewModelProvider(
    factory: ViewModelProvider.Factory,
    model: KClass<T>
): T{
    return ViewModelProvider(this, factory).get(model.java)
}


fun Boolean.runIfTrue(block: () -> Unit){
    if(this) {
        block()
    }
}

fun CallErrors.getMessage(context: Context): String {
    return when(this){
        is CallErrors.ErrorEmptyData -> context.getString(R.string.error_empty_data)
        is CallErrors.ErrorServer -> context.getString(R.string.error_server_error)
        is CallErrors.ErrorException -> context.getString(R.string.error_exception)
    }
}
