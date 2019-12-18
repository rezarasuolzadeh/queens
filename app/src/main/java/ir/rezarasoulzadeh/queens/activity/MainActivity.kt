package ir.rezarasoulzadeh.queens.activity

import android.os.Bundle
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import ir.rezarasoulzadeh.queens.R
import ir.rezarasoulzadeh.queens.adapter.CustomGridViewAdapter
import ir.rezarasoulzadeh.queens.evaluate.QueensLocations
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    private var queens = ArrayList<String>()
    private var queensLocations = ArrayList<Int>()
    private var queensCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)

        supportActionBar!!.hide()

        val gridView = findViewById<GridView>(R.id.chessBoard)

        gridView.adapter =
            CustomGridViewAdapter(
                this,
                queensLocations
            )

        plusButton.setOnClickListener {
            val currentQueen = queenNumberTextView.text.toString().toInt()
            queenNumberTextView.text = (currentQueen + 1).toString()
        }

        minusButton.setOnClickListener {
            val currentQueen = queenNumberTextView.text.toString().toInt()
            queenNumberTextView.text = (currentQueen - 1).toString()
        }

        arrangeButton.setOnClickListener {
            queensCount = queenNumberTextView.text.toString().toInt()
            queens = arrayListOf("1 5", "2 7", "3 1", "4 3", "5 8", "6 6", "7 4", "8 2")
//                               [0 4,   1 6,   2 0,   3 2,   4 7,   5 5,   6 3,   7 1]
            queensLocations = QueensLocations(
                queens,
                queensCount
            ).getQueensLocations()
            gridView.adapter =
                CustomGridViewAdapter(
                    this,
                    queensLocations
                )
        }

    }
}
