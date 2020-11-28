package com.android.fundamentals.workshop04.task

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.android.fundamentals.R

class WS04AssignmentDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_ws04, container, false)
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog?.window?.setLayout(width, height)
        dialog?.findViewById<View>(R.id.btn_cancel)?.setOnClickListener {
            dialog?.cancel()
            Toast.makeText(context, "cancel", Toast.LENGTH_SHORT).show()
        }
        dialog?.findViewById<View>(R.id.btn_ok)?.setOnClickListener {
            dialog?.cancel()
            Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        Toast.makeText(context, "cancel dialog", Toast.LENGTH_SHORT).show()
    }
}