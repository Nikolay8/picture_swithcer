package com.example.picture_switcher.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

class KeyboardUtil {
    fun showSoftKeyboard(view: View) {
        try {
            view.requestFocus()
            view.postDelayed({
                val imm =
                    view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
            }, 100)
        } catch (ne: NullPointerException) {
        }
    }

    fun hideSoftKeyboard(view: View?) {
        if (view == null) {
            return
        }
        view.clearFocus()
        view.postDelayed(Runnable {
            val imm =
                view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }, 100)
    }
}