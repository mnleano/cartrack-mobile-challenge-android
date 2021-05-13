package com.neds.cartrackmobilechallenge.ui.util
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.AutoCompleteTextView
import android.widget.EditText

object KeyboardUtil {
    /**
     * Show keyboard and focus to given EditText
     *
     * @param context Context
     * @param target  EditText to focus
     */
    fun showKeyboard(context: Context?, target: EditText?) {
        if (context == null || target == null) {
            return
        }

        val imm = getInputMethodManager(context)

        imm.showSoftInput(target, InputMethodManager.SHOW_IMPLICIT)
    }

    /**
     * Show keyboard and focus to given EditText.
     * Use this method if target EditText is in Dialog.
     *
     * @param dialog Dialog
     * @param target EditText to focus
     */
    fun showKeyboardInDialog(dialog: Dialog?, target: EditText?) {
        if (dialog == null || target == null) {
            return
        }

        dialog.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        target.requestFocus()
    }

    /**
     * hide keyboard
     *
     * @param context Context
     * @param target  View that currently has focus
     */
    fun hideKeyboard(context: Context?, target: View?) {
        if (context == null || target == null) {
            return
        }

        val imm = getInputMethodManager(context)
        imm.hideSoftInputFromWindow(target.windowToken, 0)
    }

    /**
     * hide keyboard
     *
     * @param activity Activity
     */
    fun hideKeyboard(activity: Activity) {
        val view = activity.window.decorView
            hideKeyboard(activity, view)
    }

    private fun getInputMethodManager(context: Context): InputMethodManager {
        return context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }


    @SuppressLint("ClickableViewAccessibility")
    fun setupUI(activity: Activity, view: View) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (view !is EditText || view !is AutoCompleteTextView) {
            view.setOnTouchListener { _: View, _: MotionEvent ->
                hideSoftKeyboard(activity)
                false
            }
        }

        //If a layout container, iterate over children and seed recursion.
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                setupUI(activity, innerView)
            }
        }
    }

    private fun hideSoftKeyboard(activity: Activity) {

        val inputManager = activity.getSystemService(
                Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val focusedView = activity.currentFocus

        if (focusedView != null) {
            inputManager.hideSoftInputFromWindow(focusedView.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS)
        }

    }
}