package com.example.movie.common.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

fun View.applySystemBarInsets() {
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        insets
    }
}

fun EditText.addPhoneNumberFormatting() {
    this.filters = arrayOf(android.text.InputFilter.LengthFilter(13))
    this.addTextChangedListener(object : TextWatcher {

        private var isEditing = false


        override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
            if (!isEditing) {
                isEditing = true
                if (!charSequence.startsWith("+994") && charSequence.isNotEmpty()) {
                    setText("+994$charSequence")
                    setSelection(this@addPhoneNumberFormatting.text.length)
                }
                isEditing = false
            }
        }

        override fun afterTextChanged(editable: Editable?) {
            val text = editable.toString()
            if (text.count { it == '+' } > 1) {
                setText("+994")
                setSelection(this@addPhoneNumberFormatting.text.length)
            }
        }
    })
}
fun EditText.addEmailFormatting() {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            val text = s.toString()


            val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
            val isValid = text.matches(emailRegex.toRegex())

            if (!isValid && text.isNotEmpty()) {
                this@addEmailFormatting.error = "Invalid email address"
            }
        }


    })
}

