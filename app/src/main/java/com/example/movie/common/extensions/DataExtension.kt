package com.example.movie.common.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
fun Double.toRoundedString(decimalPlaces: Int = 1): String {
    return String.format("%.${decimalPlaces}f", this)
}

fun String.reverseReleaseDate(): String {

    try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.US)
        val date = inputFormat.parse(this)
        return outputFormat.format(date)
    } catch (e: Exception) {
        return ""
        e.printStackTrace()

    }



}



fun <T> LiveData<T>.observeInIO(scope: CoroutineScope, onChange: (T) -> Unit) {
    this.observeForever { value ->
        if (value != null) {
            scope.launch(Dispatchers.IO) {
                onChange(value)
            }
        }
    }
}

