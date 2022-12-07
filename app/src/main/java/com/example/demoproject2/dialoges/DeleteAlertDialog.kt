package com.example.demoproject2.dialoges

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.demoproject2.R
import kotlinx.android.synthetic.main.confirmation_dialog.view.*

class DeleteAlertDialog :
    DialogFragment() {

    var title: String = ""
    var description: String = ""
    var deleteButtonText: String = ""
    var cancelButtonText: String = ""


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = R.style.FadingDialogAnimation
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.confirmation_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titleString = title
        view.apply {
            title.text = titleString
            des.text = description
            btn_delete.text = deleteButtonText
            btn_cancel.text = cancelButtonText
            btn_delete.setOnClickListener {
                listener?.onDeleteClick()
                dismiss()
            }
            btn_cancel.setOnClickListener {
                listener?.onCancelClick()
                dismiss()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {
            title = it.getString("title") ?: ""
            description = it.getString("discription") ?: getString(R.string.are_you_sure_delete)
            deleteButtonText = it.getString("delete") ?: getString(R.string.delete)
            cancelButtonText = it.getString("cancel") ?: getString(R.string.cancel)

            setStyle(
                STYLE_NORMAL,
                R.style.Theme_AppCompat_Translucent
            )

        }
    }


    var listener: DialogListener? = null

    interface DialogListener {
        fun onDeleteClick()
        fun onCancelClick()
    }

    fun setDialogListener(dialogListener: DialogListener) {
        this.listener = dialogListener
    }
}