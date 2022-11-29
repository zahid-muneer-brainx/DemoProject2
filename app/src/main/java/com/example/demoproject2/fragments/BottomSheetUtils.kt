package com.example.demoproject2.fragments

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.demoproject2.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BottomSheetUtils : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_BottomSheet)
    }
}