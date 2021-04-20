package com.jjg.testmvvm.model.util.font

import android.content.Context
import android.graphics.Typeface

class TypefaceUtil {
    companion object {
        private var instance: TypefaceUtil? = null

        @Synchronized
        fun getInstance(): TypefaceUtil? {
            if (instance == null) {
                instance = TypefaceUtil()
            }
            return instance
        }
    }

    //Sample
//    fun font(context: Context?): Typeface {
//        return Typeface.create(
//            ResourcesCompat.getFont(context!!, R.font.font_path),
//            Typeface.NORMAL
//        )
//    }
}