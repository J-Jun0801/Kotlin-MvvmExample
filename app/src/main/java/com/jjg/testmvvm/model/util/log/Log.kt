package com.jjg.testmvvm.model.util.log

import android.util.Log

object Log {
    private var TAG = javaClass.name

    @Synchronized
    fun v(message: String) {
        Log.v(TAG, convertLog(message))
    }

    @Synchronized
    fun d(message: String) {
        Log.d(TAG, convertLog(message))
    }

    @Synchronized
    fun i(message: String) {
        Log.i(TAG, convertLog(message))
    }

    @Synchronized
    fun w(message: String) {
        Log.w(TAG, convertLog(message))
    }

    @Synchronized
    fun e(message: String) {
        Log.e(TAG, convertLog(message))
    }

    private fun convertLog(message: String): String {
        val ste = Thread.currentThread().stackTrace[4]
        val sb = StringBuilder()
        sb.append(" [")
        sb.append(ste.fileName)
        sb.append(".")
        sb.append(ste.methodName)
        sb.append("] ")
        sb.append(message)
        return sb.toString()
    }
}