package ir.rezarasuolzadeh.queens.view.activity

import android.util.DisplayMetrics
import android.widget.GridView
import ir.rezarasuolzadeh.queens.R
import ir.rezarasuolzadeh.queens.databinding.ActivityQueenBinding
import ir.rezarasuolzadeh.queens.extensions.toastMessage
import ir.rezarasuolzadeh.queens.service.base.BaseActivity
import ir.rezarasuolzadeh.queens.service.utils.Queen
import ir.rezarasuolzadeh.queens.service.utils.QueensLocations
import ir.rezarasuolzadeh.queens.view.adapter.ChessAdapter

class QueenActivity : BaseActivity<ActivityQueenBinding>(
    ActivityQueenBinding::inflate
) {

    private var queens = ArrayList<String>()
    private var queensLocations = ArrayList<Int>()
    private var queensCount = 0

    override fun onAfterCreate() {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        val width = displayMetrics.widthPixels

        val gridView = findViewById<GridView>(R.id.chessBoard)
        gridView.numColumns = 8

        for (i in 0 until 8) {
            queensLocations.add(-1)
        }

        gridView.adapter = ChessAdapter(this, queensLocations, width)

        binding.plusButton.setOnClickListener {
            if (binding.queenNumberTextView.text.toString().toInt() == 40) {
                toastMessage(message = "امکان پذیر نیست", inflater = this.layoutInflater)
            } else {
                val currentQueen = binding.queenNumberTextView.text.toString().toInt()
                binding.queenNumberTextView.text = (currentQueen + 1).toString()
            }
        }

        binding.minusButton.setOnClickListener {
            if (binding.queenNumberTextView.text.toString().toInt() == 4) {
                toastMessage(message = "امکان پذیر نیست", inflater = this.layoutInflater)
            } else {
                val currentQueen = binding.queenNumberTextView.text.toString().toInt()
                binding.queenNumberTextView.text = (currentQueen - 1).toString()
            }
        }

        binding.arrangeButton.setOnClickListener {
            queensCount = binding.queenNumberTextView.text.toString().toInt()
            queens = Queen.solution(queensCount)
            queensLocations = QueensLocations(queens, queensCount).getQueensLocations()
            gridView.numColumns = queensCount
            gridView.adapter = ChessAdapter(this, queensLocations, width)
        }
    }

}