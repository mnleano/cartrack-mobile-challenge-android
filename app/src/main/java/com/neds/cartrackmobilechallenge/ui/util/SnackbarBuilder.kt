package com.neds.cartrackmobilechallenge.ui.util

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.neds.cartrackmobilechallenge.R

// ANTON Do we need these to be in the core sdk? All the colors are hardcoded and it doesn't appear to save
// you anything.
object SnackbarBuilder {

    interface SnackbarListener {
        fun onDismissed()
    }

    fun show(
        view: View,
        message: String,
        length: Int = Snackbar.LENGTH_LONG,
        error: Boolean = false
    ) {
        val snackBar = builder(view, message, length, error)

        snackBar.show()
    }

    fun show(
        view: View,
        message: String,
        action: String,
        actionListener: View.OnClickListener,
        length: Int = Snackbar.LENGTH_SHORT,
    ) {
        val snackBar = builder(view, message, length)
        snackBar.setAction(action, actionListener)
        snackBar.show()
    }

    fun showWithDismissListener(
        view: View,
        message: String,
        length: Int = Snackbar.LENGTH_SHORT,
        listener: SnackbarListener
    ) {
        val snackBar = builder(view, message, length)

        snackBar.addCallback(object : Snackbar.Callback() {
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                super.onDismissed(transientBottomBar, event)
                listener.onDismissed()
            }
        })
        snackBar.show()
    }

    private fun builder(
        view: View,
        message: String,
        length: Int = Snackbar.LENGTH_LONG,
        error: Boolean = false
    ): Snackbar {
        val snackBar = Snackbar.make(view, message, length)
        val sbView = snackBar.view
        sbView.setBackgroundColor(ContextCompat.getColor(view.context, R.color.white))
        val textView = sbView.findViewById<TextView>(R.id.snackbar_text)

        // Color can be overridden on the app level
        if (error)
            textView.setTextColor(ContextCompat.getColor(view.context, R.color.text_color_red))
        else {
            textView.setTextColor(ContextCompat.getColor(view.context, R.color.text_color_black))
        }
        return snackBar
    }
}
