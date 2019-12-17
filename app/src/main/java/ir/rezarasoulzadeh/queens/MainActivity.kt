package ir.rezarasoulzadeh.queens

import android.os.Bundle
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
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

        gridView.adapter = CustomGridViewAdapter(this, queensLocations)

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
//            queensLocations = QueensLocations(queens, queensCount).getQueensLocations()
            queensLocations = arrayListOf(1, 8, 20, 31, 45, 51)
            gridView.adapter = CustomGridViewAdapter(this, queensLocations)
        }

    }
}
