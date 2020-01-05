package ir.rezarasoulzadeh.queens.activity

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import ir.rezarasoulzadeh.queens.R
import ir.rezarasoulzadeh.queens.adapter.ChessBoardAdapter
import ir.rezarasoulzadeh.queens.evaluate.QueensLocations
import ir.rezarasoulzadeh.queens.solution.Queen
import ir.rezarasoulzadeh.queens.toast.CustomToast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var queens = ArrayList<String>()
    private var queensLocations = ArrayList<Int>()
    private var queensCount = 0
    lateinit var customToast: CustomToast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)

        supportActionBar!!.hide()

        // get device dimensions
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
            val currentQueen = queenNumberTextView.text.toString().toInt()
            queenNumberTextView.text = (currentQueen + 1).toString()
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
}
