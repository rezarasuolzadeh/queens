package ir.rezarasoulzadeh.queens.service.utils

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import ir.rezarasoulzadeh.queens.databinding.LayoutForToastBinding

class CustomToast(private val context: Context, private val inflater: LayoutInflater) {

    fun show(message: String, duration: String) {
        val customToast = Toast(context)
        val customToastView = LayoutForToastBinding.inflate(inflater)
        customToast.setGravity(Gravity.CENTER, 0, 0)
        customToast.view = customToastView.root
        customToastView.toastText.text = message
        if (duration == "long")
            customToast.duration = Toast.LENGTH_LONG
        if (duration == "short")
            customToast.duration = Toast.LENGTH_SHORT
        customToast.show()
    }

}