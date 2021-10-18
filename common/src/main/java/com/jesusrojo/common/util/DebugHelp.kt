package com.jesusrojo.common.util

import android.util.Log
//import logcat.logcat
//import timber.log.Timber

// _UP DEBUG
class DebugHelp {

    companion object{

        const val IS_DEBUG: Boolean = true

        private const val jrTag = "##"

        var fullLog = ""

        fun l(classSimpleName: String, message: String) {
            fullLog += "\n$message"
            l("$classSimpleName $message $jrTag")
        }

        fun l(message: String) {
            fullLog += "\n$message"
            Log.d(jrTag, message)
          //  Timber.d("$message $jrTag")
//            logcat { message }
        }

        fun le(message: String) {
            fullLog += "\n$message"
            Log.d(jrTag, message)
            //  Timber.e("$message $jrTag")
//            logcat { message }
        }

//        fun lt(message: String) {
//            fullLog += "\n$message"
//            Log.d(jrTag, message)
//            Timber.d("$message THREAD: ${Thread.currentThread().name} $jrTag")
//        }
    }
}