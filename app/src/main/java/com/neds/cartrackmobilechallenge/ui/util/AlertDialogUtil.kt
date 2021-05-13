package com.neds.cartrackmobilechallenge.ui.util

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

object AlertDialogUtil {

    private fun getDialogBuilder(ctx: Context): AlertDialog.Builder {
        return AlertDialog.Builder(ctx)
    }

    private fun show(builder: AlertDialog.Builder) {
        builder.create().show()
    }

    fun show(
        ctx: Context,
        title: String? = null,
        message: String? = null,
        positiveButton: String? = null,
        positiveClickListener: DialogInterface.OnClickListener? = null,
        negativeButton: String? = null,
        negativeClickListener: DialogInterface.OnClickListener? = null,
        cancellable: Boolean = true
    ) {
        val builder = getDialogBuilder(ctx)
        title?.let {
            builder.setTitle(title)
        }

        message?.let {
            builder.setMessage(message)
        }

        builder.setPositiveButton(
            positiveButton ?: ctx.getString(android.R.string.ok),
            positiveClickListener
                ?: DialogInterface.OnClickListener { dialog, _ -> dialog.dismiss() })

        negativeButton?.let {
            builder.setNegativeButton(
                negativeButton,
                negativeClickListener
                    ?: DialogInterface.OnClickListener { dialog, _ -> dialog.dismiss() })
        }
        builder.setCancelable(cancellable)
        show(builder)
    }
}