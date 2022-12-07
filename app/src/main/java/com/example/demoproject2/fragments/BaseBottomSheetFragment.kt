package com.example.demoproject2.fragments



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

abstract class BaseBottomSheetFragment< VB : ViewDataBinding>:BottomSheetUtils() {
    //region Properties
    protected lateinit var mViewBinding: VB
    //endregion

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getViewBinding().apply {
            lifecycleOwner = this@BaseBottomSheetFragment
            mViewBinding = this
        }
        customOnViewCreated(savedInstanceState)
        return mViewBinding.root
    }

    abstract fun getViewBinding(): VB

    abstract fun customOnViewCreated(savedInstanceState: Bundle?)

    fun setBottomSheetBehaviourState(behaviorState:Int){
        BottomSheetBehavior.from(mViewBinding.root?.parent as View)?.apply {
            state = behaviorState
        }
    }

    //endregion
}