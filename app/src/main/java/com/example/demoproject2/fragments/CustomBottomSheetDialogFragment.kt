package com.example.demoproject2.fragments

import android.app.Dialog
import android.os.Bundle
import com.example.demoproject2.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class CustomBottomSheetDialogFragment : BottomSheetDialogFragment() {

    override fun getTheme(): Int =
        R.style.CustomBottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(requireContext(), theme)


}