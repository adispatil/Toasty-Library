package com.aditya.toastylibrary

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast

object ToastHelper {
    /**
     * Show a short toast safely from any thread.
     * @param context Context used to show the toast. Prefer Application context.
     * @param message The text to display.
     */
    fun showShort(context: Context, message: String) {
        show(context, message, Toast.LENGTH_SHORT)
    }

    /**
     * Show a long toast safely from any thread.
     */
    fun showLong(context: Context, message: String) {
        show(context, message, Toast.LENGTH_LONG)
    }

    private fun show(context: Context, message: String, duration: Int) {
        val appCtx = context.applicationContext
        val prefixMessage = "This toast is from ToastyLibrary. \uD83D\uDE0A"
        val fullMessage = "$prefixMessage\n$message"
        // Ensure toast runs on main thread
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Toast.makeText(appCtx, fullMessage, duration).show()
        } else {
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(appCtx, fullMessage, duration).show()
            }
        }
    }
}