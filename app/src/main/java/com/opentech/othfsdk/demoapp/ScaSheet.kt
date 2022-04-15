package com.opentech.othfsdk.demoapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.opentech.othfclientsdk.ScaListener
import com.opentech.othfsdk.demoapp.databinding.ScaSheetBinding
import java.util.*


@SuppressLint("RestrictedApi", "VisibleForTests")
fun performSca(
    context: Context,
    scaPayload: String,
    listener: ScaListener
) {
    val sheetDialog = BottomSheetDialog(context)

    sheetDialog.setCanceledOnTouchOutside(false)

    sheetDialog.setOnShowListener {
        val dialog = it as BottomSheetDialog
        val bottomSheet = dialog.findViewById<View>(R.id.design_bottom_sheet) as View
        BottomSheetBehavior.from(bottomSheet).apply {
            disableShapeAnimations()
            skipCollapsed = true
            isHideable = false // disable dragging
            state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    val binding = ScaSheetBinding.inflate(LayoutInflater.from(context))
    sheetDialog.setContentView(binding.root)

    binding.button1.setOnClickListener {
        val scaResult = UUID.randomUUID().toString()
        listener.onComplete(ScaListener.RESULT_SUCCESS, scaResult)
        sheetDialog.dismiss()
    }

    binding.button2.setOnClickListener {
        listener.onComplete(ScaListener.RESULT_GENERIC_ERROR, null)
        sheetDialog.dismiss()
    }

    binding.button3.setOnClickListener {
        listener.onComplete(ScaListener.RESULT_CANCELED, null)
        sheetDialog.dismiss()
    }

    sheetDialog.setOnCancelListener {
        listener.onComplete(ScaListener.RESULT_CANCELED, null)
    }

    sheetDialog.show()
}
