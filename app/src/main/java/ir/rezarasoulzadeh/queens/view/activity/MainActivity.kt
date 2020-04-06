package ir.rezarasoulzadeh.queens.view.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.widget.GridView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import ir.rezarasoulzadeh.queens.R
import ir.rezarasoulzadeh.queens.view.adapter.ChessBoardAdapter
import ir.rezarasoulzadeh.queens.service.utils.QueensLocations
import ir.rezarasoulzadeh.queens.service.utils.Queen
import ir.rezarasoulzadeh.queens.service.utils.CustomToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_for_exit.view.*
import java.lang.Exception
import java.util.*


class MainActivity : AppCompatActivity() {

    private var queens = ArrayList<String>()
    private var queensLocations = ArrayList<Int>()
    private var queensCount = 0
    private lateinit var customToast: CustomToast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        val width = displayMetrics.widthPixels

        val gridView = findViewById<GridView>(R.id.chessBoard)
        gridView.numColumns = 8

        for (i in 0 until 8) {
            queensLocations.add(-1)
        }

        gridView.adapter = ChessBoardAdapter(this, queensLocations, width)

        val context = this.applicationContext!!
        val inflater = this.layoutInflater

        customToast = CustomToast(context, inflater)

        plusButton.setOnClickListener {
            if (queenNumberTextView.text.toString().toInt() == 40) {
                customToast.show("امکان پذیر نیست", "short")
            } else {
                val currentQueen = queenNumberTextView.text.toString().toInt()
                queenNumberTextView.text = (currentQueen + 1).toString()
            }
        }

        minusButton.setOnClickListener {
            if (queenNumberTextView.text.toString().toInt() == 4) {
                customToast.show("امکان پذیر نیست", "short")
            } else {
                val currentQueen = queenNumberTextView.text.toString().toInt()
                queenNumberTextView.text = (currentQueen - 1).toString()
            }
        }

        arrangeButton.setOnClickListener {
            queensCount = queenNumberTextView.text.toString().toInt()
            queens = Queen.solution(queensCount)
            queensLocations = QueensLocations(queens, queensCount).getQueensLocations()
            gridView.numColumns = queensCount
            gridView.adapter = ChessBoardAdapter(this, queensLocations, width)
        }
    }

    override fun onBackPressed() {
        val exitView = LayoutInflater.from(this).inflate(R.layout.dialog_for_exit, null)

        val exitViewBuilder = this.let { it1 -> AlertDialog.Builder(it1).setView(exitView) }

        val exitAlertDialog = exitViewBuilder.show()

        exitAlertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        exitAlertDialog.setCanceledOnTouchOutside(false)

        exitView.exitButton.setOnClickListener {
            this.finish()
        }

        exitView.starButton.setOnClickListener {
            try {
                val url = "myket://comment?id=ir.rezarasoulzadeh.queens"
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.data = Uri.parse(url)
                startActivity(intent)
                exitAlertDialog.dismiss()
            } catch (e: Exception) {
                customToast.show("ابتدا مایکت را نصب کنید", "short")
            }
        }

        exitView.developerButton.setOnClickListener {
            try {
                val url = "myket://developer/ir.rezarasoulzadeh.queens"
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.data = Uri.parse(url)
                startActivity(intent)
                exitAlertDialog.dismiss()
            } catch (e: Exception) {
                customToast.show("ابتدا مایکت را نصب کنید", "short")
            }
        }

        exitView.mailButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.type = "text/plain"
            intent.data = Uri.parse("mailto:")
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("reza.rassoulzadeh@gmail.com"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "معمای وزیر ها")
            startActivity(Intent.createChooser(intent, "ارسال پیام از طریق:"))
            exitAlertDialog.dismiss()
        }

        exitView.shareButton.setOnClickListener {
            val productShareBody =
                "معمای وزیر ها\nوزیر ها رو درست بچین\nلینک نصب:\n" + this.resources.getString(
                    R.string.myketLink
                )
            val productShareIntent = Intent(Intent.ACTION_SEND)
            productShareIntent.type = "text/plain"
            productShareIntent.putExtra(Intent.EXTRA_SUBJECT, "معمای وزیر ها")
            productShareIntent.putExtra(Intent.EXTRA_TEXT, productShareBody)
            startActivity(Intent.createChooser(productShareIntent, "معرفی به دوستان"))
            exitAlertDialog.dismiss()
        }
    }

}