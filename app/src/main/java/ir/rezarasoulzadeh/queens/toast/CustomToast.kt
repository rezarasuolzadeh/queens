package ir.rezarasoulzadeh.queens.toast

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import ir.rezarasoulzadeh.queens.R
import kotlinx.android.synthetic.main.toast.view.*

class CustomToast(private val context: Context, private val inflater: LayoutInflater) {

    fun show(message: String, duration: String) {

        val customToast = Toast(context)

        val customToastView: View = inflater.inflate(R.layout.toast, null)

        customToast.setGravity(Gravity.CENTER, 0, 0)
        customToast.view = customToastView
        customToastView.toastText.text = message

        if (duration == "long")
            customToast.duration = Toast.LENGTH_LONG
        if (duration == "short")
            customToast.duration = Toast.LENGTH_SHORT

        customToast.show()
    }

}