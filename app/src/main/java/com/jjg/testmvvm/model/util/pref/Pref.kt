package com.jjg.testmvvm.model.util.pref

import android.content.Context
import android.content.SharedPreferences

object Pref {
    private var PREF_NAME: String = ""
    private lateinit var preference: SharedPreferences

    fun init(context: Context) {
        preference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun getString(id: String): String {
        return preference.getString(id, "")!!
    }

    fun getBoolean(id: String): Boolean {
        return preference.getBoolean(id, false)
    }

    fun getStringSet(id: String): HashSet<String> {
        return preference.getStringSet(id, HashSet<String>()) as HashSet<String>
    }

    fun setValue(id: String, value: String) {
        val editor = preference.edit()
        editor.putString(id, value)
        editor.apply()
    }

    fun setValue(id: String, value: Int) {
        val editor = preference.edit()
        editor.putInt(id, value)
        editor.apply()
    }

    fun setValue(id: String, value: Long) {
        val editor = preference.edit()
        editor.putLong(id, value)
        editor.apply()
    }

    fun setValue(id: String, value: Boolean) {
        val editor = preference.edit()
        editor.putBoolean(id, value)
        editor.apply()
    }

    fun setValue(id: String, value: Float) {
        val editor = preference.edit()
        editor.putFloat(id, value)
        editor.apply()
    }
    fun setValue(id: String, value: HashSet<String>) {
        val editor = preference.edit()
        editor.putStringSet(id, value)
        editor.apply()
    }
}