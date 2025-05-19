package ir.rezarasoulzadeh.queens.view.activity

import android.util.DisplayMetrics
import android.widget.GridView
import ir.rezarasoulzadeh.queens.R
import ir.rezarasoulzadeh.queens.databinding.ActivityForQueenBinding
import ir.rezarasoulzadeh.queens.service.base.BaseActivity
import ir.rezarasoulzadeh.queens.service.utils.CustomToast
import ir.rezarasoulzadeh.queens.service.utils.Queen
import ir.rezarasoulzadeh.queens.service.utils.QueensLocations
import ir.rezarasoulzadeh.queens.view.adapter.ChessAdapter

class QueenActivity : BaseActivity<ActivityForQueenBinding>(
    ActivityForQueenBinding::inflate
) {

    private var queens = ArrayList<String>()
    private var queensLocations = ArrayList<Int>()
    private var queensCount = 0
    private lateinit var customToast: CustomToast

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

        val context = this.applicationContext!!
        val inflater = this.layoutInflater

        customToast = CustomToast(context, inflater)

        binding.plusButton.setOnClickListener {
            if (binding.queenNumberTextView.text.toString().toInt() == 40) {
                customToast.show("امکان پذیر نیست", "short")
            } else {
                val currentQueen = binding.queenNumberTextView.text.toString().toInt()
                binding.queenNumberTextView.text = (currentQueen + 1).toString()
            }
        }

        binding.minusButton.setOnClickListener {
            if (binding.queenNumberTextView.text.toString().toInt() == 4) {
                customToast.show("امکان پذیر نیست", "short")
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