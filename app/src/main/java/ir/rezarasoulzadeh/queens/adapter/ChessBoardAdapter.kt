package ir.rezarasoulzadeh.queens.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView
import ir.rezarasoulzadeh.queens.R

class ChessBoardAdapter(private val context: Context, private val queens: ArrayList<Int>) :
    BaseAdapter() {

    private val cells = ArrayList<Int>()

    init {
        var cell = R.drawable.white
        var counter = 0
        for (i in 0 until ((queens.size) * (queens.size))) {
            cells.add(cell)
            if (counter != queens.size - 1) {
                if (cell == R.drawable.white)
                    cell = R.drawable.black
                else
                    cell = R.drawable.white
            }
            counter++
            counter = counter % queens.size - 1
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
                if (cells[position] == R.drawable.white) {
                    imageView.setImageResource(R.drawable.white_queen)
                }
                if (cells[position] == R.drawable.black) {
                    imageView.setImageResource(R.drawable.black_queen)
                }
            } else {
                imageView.setImageResource(cells[position])
            }
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.layoutParams = AbsListView.LayoutParams(120, 120)
        }
        return imageView
    }

}