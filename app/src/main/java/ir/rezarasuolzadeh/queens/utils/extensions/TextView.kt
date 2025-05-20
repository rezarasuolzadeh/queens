package ir.rezarasuolzadeh.queens.utils.extensions

import android.widget.TextView

/**
 * extract integer value from a text view.
 */
fun TextView.getInteger() = text.toString().toInt()