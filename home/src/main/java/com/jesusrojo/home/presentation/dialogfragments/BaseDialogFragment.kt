package com.jesusrojo.home.presentation.dialogfragments

import android.app.Dialog
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment


abstract class BaseDialogFragment : DialogFragment() {

    override fun onResume() {
        super.onResume()
        val myDialog = dialog
        setDialogMatchWrap(myDialog)
    }

    private fun setDialogMatchWrap(dialog: Dialog?) {
        if (dialog != null) {
            val window = dialog.window
            if (window != null) {
                val params = window.attributes
                if (params != null) {
                    params.width = ViewGroup.LayoutParams.MATCH_PARENT
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT
                    window.attributes = params
                }
            }
        }
    }

    protected fun dismissMyDialog() {
        try {
            dismiss()
        } catch (e: Exception) {
           // Timber.e("Error $e")
        }
    }

    companion object {

        fun showMyDialogFragment(a: AppCompatActivity?,
                                         fragment: DialogFragment?) {
            if (a == null || fragment == null) return
            try {
                val aClass: Class<out DialogFragment> = fragment.javaClass
                if (aClass != null) {
                    val simpleName = aClass.simpleName
                    if (simpleName != null) {
                        val tag = simpleName + "_tag"
                        fragment.show(a.supportFragmentManager, tag)
                    }
                }
            } catch (e: IllegalStateException) { //error developer
              //  Timber.e("ko $e")
            } catch (e: Exception) {
              //  Timber.e("ko $e")
            }
        }
    }
}