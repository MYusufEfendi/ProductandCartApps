package com.yusuf.app.testtwiscode.ui

import android.app.ProgressDialog
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.yusuf.app.testtwiscode.R

open class BasePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val view = window.decorView
            var flags = view.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
////            flags = flags or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
////            flags = flags or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            view.systemUiVisibility = flags
        } else {
            window.statusBarColor = ContextCompat.getColor(this, R.color.teal_200)
        }

//        if (BuildConfig.BUILD_TYPE != "release") {
//            window.statusBarColor =
//                Color.parseColor(if (BuildConfig.BUILD_TYPE == "debug") "#53C0F1" else "#F49C4D")
//        }
    }

    fun dismissloading() {
        loadingDialog?.dismiss()
    }

    var loadingDialog: ProgressDialog? = null
    fun loading(
        title: String = "Mohon tunggu",
        msg: String = "Sedang memproses ...",
        indeterminate: Boolean = true
    ) {
        dismissloading()
        loadingDialog = ProgressDialog.show(this, title, msg, indeterminate)
    }
}