package ir.rezarasuolzadeh.queens.utils.extensions

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import ir.rezarasuolzadeh.queens.databinding.ViewToastBinding

/**
 * show toast message with my customization.
 */
fun Context.toastMessage(message: String, inflater: LayoutInflater) {
    val customToast = Toast(this)
    val customToastView = ViewToastBinding.inflate(inflater)
    customToastView.toastText.text = message
    customToast.apply {
        setGravity(Gravity.CENTER, 0, 0)
        view = customToastView.root
        duration = Toast.LENGTH_SHORT
        show()
    }
}