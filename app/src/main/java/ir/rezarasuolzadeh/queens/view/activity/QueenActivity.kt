package ir.rezarasuolzadeh.queens.view.activity

import android.util.DisplayMetrics
import ir.rezarasuolzadeh.queens.databinding.ActivityQueenBinding
import ir.rezarasuolzadeh.queens.utils.base.BaseActivity
import ir.rezarasuolzadeh.queens.service.utils.Queen
import ir.rezarasuolzadeh.queens.service.utils.QueensLocations
import ir.rezarasuolzadeh.queens.utils.MAX_QUEEN_COUNT
import ir.rezarasuolzadeh.queens.utils.MIN_QUEEN_COUNT
import ir.rezarasuolzadeh.queens.utils.extensions.getInteger
import ir.rezarasuolzadeh.queens.utils.extensions.gone
import ir.rezarasuolzadeh.queens.utils.extensions.invisible
import ir.rezarasuolzadeh.queens.utils.extensions.toastMessage
import ir.rezarasuolzadeh.queens.utils.extensions.visible
import ir.rezarasuolzadeh.queens.view.adapter.ChessAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QueenActivity : BaseActivity<ActivityQueenBinding>(
    ActivityQueenBinding::inflate
) {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         variables                                          //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private var queens = ArrayList<String>()
    private var queensLocations = ArrayList<Int>()
    private var queensCount = 0

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         overrides                                          //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onAfterCreate() {
        initializeChessBoardAdapter()
        configClickListeners()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                          configs                                           //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * handle the click action of clickable views.
     */
    private fun configClickListeners() = with(binding) {
        btnIncrease.setOnClickListener {
            if (tvQueens.getInteger() == MAX_QUEEN_COUNT) {
                toastMessage(
                    message = "امکان پذیر نیست",
                    inflater = this@QueenActivity.layoutInflater
                )
            } else {
                tvQueens.text = (tvQueens.getInteger() + 1).toString()
            }
        }

        btnDecrease.setOnClickListener {
            if (tvQueens.getInteger() == MIN_QUEEN_COUNT) {
                toastMessage(
                    message = "امکان پذیر نیست",
                    inflater = this@QueenActivity.layoutInflater
                )
            } else {
                tvQueens.text = (tvQueens.getInteger() - 1).toString()
            }
        }

        btnArrange.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                showLoading()
                delay(timeMillis = 500)
                queensCount = tvQueens.getInteger()
                queens = Queen.solution(queensCount)
                queensLocations = QueensLocations(
                    queensLocationsString = queens,
                    queensCount = queensCount
                ).getQueensLocations()
                reInitializeChessBoardAdapter()
                hideLoading()
            }
        }
    }

    /**
     * calculate the screen width and return the result in integer format.
     */
    private fun calculateScreenWidth(): Int {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

    /**
     * update the UI for showing the loading.
     */
    private fun showLoading() = with(binding) {
        CoroutineScope(Dispatchers.Main).launch {
            btnArrange.invisible()
            vLoading.visible()
        }
    }

    /**
     * update the UI for hide the loading (default UI).
     */
    private fun hideLoading() = with(binding) {
        CoroutineScope(Dispatchers.Main).launch {
            btnArrange.visible()
            vLoading.gone()
        }
    }

    /**
     * initialize the chess board adapter with default 8 queen.
     */
    private fun initializeChessBoardAdapter() = CoroutineScope(Dispatchers.Main).launch {
        binding.gvChessBoard.numColumns = 8
        for (i in 0 until 8) {
            queensLocations.add(-1)
        }
        binding.gvChessBoard.adapter = ChessAdapter(
            context = this@QueenActivity,
            queens = queensLocations,
            width = calculateScreenWidth()
        )
    }

    /**
     * initialize the chess board with choose queens.
     */
    private fun reInitializeChessBoardAdapter() = CoroutineScope(Dispatchers.Main).launch {
        binding.gvChessBoard.numColumns = queensCount
        binding.gvChessBoard.adapter = ChessAdapter(
            context = this@QueenActivity,
            queens = queensLocations,
            width = calculateScreenWidth()
        )
    }

}