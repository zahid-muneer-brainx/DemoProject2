package com.example.demoproject2.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.demoproject2.databinding.SampleBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class ChatBottomSheet : BaseBottomSheetFragment<SampleBottomSheetBinding>(){
    override fun getViewBinding()= SampleBottomSheetBinding.inflate(layoutInflater)
    override fun customOnViewCreated(savedInstanceState: Bundle?) {
     init()
    }
fun init():View
{
    var view:View=mViewBinding.btnCancel
    mViewBinding.deleteContainer.setOnClickListener {
        dismiss()
        view=it
    }
    mViewBinding.btnCancel.setOnClickListener {
        dismiss()
        view=it
    }
    mViewBinding.btnCancel.setOnClickListener {
        dismiss()
        view=it
    }
    return view
}

}