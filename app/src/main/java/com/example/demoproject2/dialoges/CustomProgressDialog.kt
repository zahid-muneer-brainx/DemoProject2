package com.example.demoproject2.dialoges

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.example.demoproject2.R

class CustomProgressDialog(context: Context) :

    Dialog(context) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.loading_dialog)
        setTitle("")
        setCancelable(false)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}