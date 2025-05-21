package ir.rezarasuolzadeh.queens.utils.extensions

import android.view.View

/**
 * change the visibility of a view to gone.
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * change the visibility of a view to visible.
 */
fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * change the visibility of a view to invisible.
 */
fun View.invisible() {
    visibility = View.INVISIBLE
}

/**
 * change the visibility of a view to gone or visible depending on the boolean value.
 */
fun View.goneOrVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

/**
 * change the visibility of a view to invisible or visible depending on the boolean value.
 */
fun View.invisibleOrVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}

/**
 * change the visibility of a view is visible or not.
 */
fun View.isVisible() = visibility == View.VISIBLE

/**
 * change the visibility of a view is gone or not.
 */
fun View.isGone() = visibility == View.GONE

/**
 * change the visibility of a view is invisible or not.
 */
fun View.isInvisible() = visibility == View.INVISIBLE