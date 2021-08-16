package com.yusuf.app.testtwiscode.ui

import android.app.ProgressDialog
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yusuf.app.testtwiscode.R
import timber.log.Timber

open class BaseFragment : Fragment(){
    var loadingDialog: ProgressDialog? = null
    fun loading(
        title: String = "Mohon tunggu",
        msg: String = "Sedang memproses ...",
        indeterminate: Boolean = true
    ) {
        loadingDialog?.dismiss()
        loadingDialog = ProgressDialog.show(requireContext(), title, msg, indeterminate)
    }
    fun dismissloading() {
        loadingDialog?.dismiss()
    }

    fun toast(msg: String) {
        Timber.e("show toast: $msg\nfrom: ${javaClass.simpleName}")
        if (msg.isNotEmpty())
            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    var alertDialog: AlertDialog? = null

//    fun alertSelect(
//        title: String = "",
//        items: Array<String> = emptyArray(),
//        onSelect: (pos: Int) -> Unit = {}
//    ) {
//        dismissAlert()
//        alertDialog = MaterialAlertDialogBuilder(requireContext(), R.style.DialogTheme)
//            .setTitle(title)
////            .setMessage(msg)
//            .setItems(items) { dialog, which ->
//                dialog.dismiss()
//                onSelect.invoke(which)
//            }
//            .show()
//    }

    fun alert(
        title: String = "Perhatian",
        msg: String = "",
        okLabel: String = "Tutup",
        okClick: () -> Unit = {},
        abortLabel: String = "Batal",
        abortClick: () -> Unit = {}
    ) {
        dismissAlert()
        alertDialog = MaterialAlertDialogBuilder(requireContext(), R.style.DialogTheme)
            .setTitle(title)
            .setMessage(msg)
            .setCancelable(false)
            .setPositiveButton(okLabel) { dialog, which ->
                dialog.dismiss()
                okClick.invoke()
            }
            .setNeutralButton(abortLabel) { dialog, which ->
                dialog.dismiss()
                abortClick.invoke()
            }
            .show()
    }

    fun dismissAlert() {
        alertDialog?.dismiss()
    }

}