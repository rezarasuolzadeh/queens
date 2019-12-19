package ir.rezarasoulzadeh.queens.activity

import android.os.Bundle
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import ir.rezarasoulzadeh.queens.R
import ir.rezarasoulzadeh.queens.adapter.ChessBoardAdapter
import ir.rezarasoulzadeh.queens.evaluate.QueensLocations
import ir.rezarasoulzadeh.queens.solution.Queen
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

        gridView.adapter = ChessBoardAdapter(this, queensLocations)

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
            queens = Queen.solution(queensCount)
            queensLocations = QueensLocations(queens, queensCount).getQueensLocations()
            gridView.adapter = ChessBoardAdapter(this, queensLocations)
        }

    }
}