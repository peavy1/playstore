package com.test.myapplication.profilebottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.test.myapplication.MainViewModel
import com.test.myapplication.R

class ProfileBottomSheetFragment : BottomSheetDialogFragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

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
                    onProfileChangeClick = {
                        mainViewModel.onSignOutClicked()
                    },
                    onCloseClick = { dismiss() }
                )
            }
        }
    }
}
