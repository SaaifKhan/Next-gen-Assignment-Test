package com.saif.base.extensions

import androidx.lifecycle.MutableLiveData

/*Live data extension function which'll set initial value*/
fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }
