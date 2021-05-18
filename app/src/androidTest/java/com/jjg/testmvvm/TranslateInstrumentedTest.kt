package com.jjg.testmvvm

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.jjg.testmvvm.ui.activity.TranslateActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

/**
 * Espressso
 * UI관련 테스트
 */
@RunWith(AndroidJUnit4::class)
class TranslateInstrumentedTest {
    @Rule
    @JvmField
    var activityTestRule: ActivityTestRule<TranslateActivity> =
        ActivityTestRule(TranslateActivity::class.java)

    @Test
    fun translate(){
        onView(withId(R.id.et_translate))
            .perform(replaceText("즐거운 금요일 :)"))

        onView(withId(R.id.btn_confirm))
            .perform(click())
    }
}