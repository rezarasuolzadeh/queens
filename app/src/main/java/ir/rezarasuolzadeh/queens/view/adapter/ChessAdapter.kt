package ir.rezarasuolzadeh.queens.view.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView
import ir.rezarasuolzadeh.queens.R

class ChessAdapter(
    private val context: Context,
    private val queens: ArrayList<Int>,
    private val width: Int
) :
    BaseAdapter() {

    private val cells = ArrayList<Int>()

    init {
        var cell = R.drawable.bg_tile_white
        var counter = 0
        for (i in 0 until ((queens.size) * (queens.size))) {
            cells.add(cell)
            if (queens.size % 2 == 0) {
                if (counter != queens.size - 1) {
                    cell = if (cell == R.drawable.bg_tile_white) R.drawable.bg_tile_black else R.drawable.bg_tile_white
                }
            } else {
                if (counter != queens.size) {
                    cell = if (cell == R.drawable.bg_tile_white) R.drawable.bg_tile_black else R.drawable.bg_tile_white
                }
            }
            counter = (counter + 1) % queens.size
        }
    }

    override fun getCount(): Int {
        return cells.size
    }

    override fun getItem(position: Int): Any {
        return cells[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView = ImageView(context)
        if (queens.isEmpty()) {
            imageView.setImageResource(cells[position])
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.layoutParams = AbsListView.LayoutParams(120, 120)
        }
        if (queens.isNotEmpty()) {
            if (position in queens) {
                if (cells[position] == R.drawable.bg_tile_white) {
                    imageView.setImageResource(R.drawable.bg_tile_white_queen)
                }
                if (cells[position] == R.drawable.bg_tile_black) {
                    imageView.setImageResource(R.drawable.bg_tile_black_queen)
                }
            } else {
                imageView.setImageResource(cells[position])
            }
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.layoutParams = AbsListView.LayoutParams((width / queens.size), (width / queens.size))
        }
        return imageView
    }

}