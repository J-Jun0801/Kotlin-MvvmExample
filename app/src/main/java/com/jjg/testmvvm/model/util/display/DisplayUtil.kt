package com.jjg.testmvvm.model.util.display

import android.content.Context

class DisplayUtil {
    companion object {

        /**
         * dp(int) to px(float)
         */
        fun dpToPx(context: Context, dp: Int): Float {
            return dpToPx(context, dp.toFloat())
        }

        /**
         * dp(float) to px(float)
         */
        private fun dpToPx(context: Context, dp: Float): Float {
            return dp * context.resources.displayMetrics.density
        }

        /**
         * px(int) to dp(float)
         */
        fun pxToDp(context: Context, px: Int): Float {
            return pxToDp(context, px.toFloat())
        }

        /**
         * px(float) to dp(float)
         */
        private fun pxToDp(context: Context, px: Float): Float {
            return px / context.resources.displayMetrics.density
        }
    }
}