package com.test.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ProfileBottomSheetFragment() : BottomSheetDialogFragment() {
    override fun getTheme(): Int {
        return R.style.MyCustomBottomSheetDialogTheme
    }

    private fun expandDialog() {
        val behavior: BottomSheetBehavior<*> = (dialog as BottomSheetDialog).behavior
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        expandDialog()
        return ComposeView(requireContext()).apply {
            setContent {
                ProfileBottomSheetContent(
                    onCloseClick = { dismiss() }
                )
            }
        }
    }
}
