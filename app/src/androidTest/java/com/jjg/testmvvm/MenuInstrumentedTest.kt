package com.jjg.testmvvm

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.jjg.testmvvm.ui.activity.MenuActivity
import org.hamcrest.CoreMatchers.allOf
import org.junit.Assert.assertEquals
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
class MenuInstrumentedTest {
    @Rule
    @JvmField
    var menuActivityTestRule: ActivityTestRule<MenuActivity> =
        ActivityTestRule(MenuActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.jjg.testmvvm", appContext.packageName)
    }

    @Test
    fun clickTranslate() {
        onView(withId(R.id.btn_translate)).perform(click())
//        Espresso.onView(withId(R.id.btn_translate)).perform(longClick())
    }

    @Test
    fun isContainTranslate() {
        onView(withId(R.id.btn_translate))
            .check(matches(withText("Translate")))
    }

    /**
     * isDisplayed()
     * 가장 기본적인 ViewMatcher로 화면에 보이는지 여부로 판별.
     * 단순히 VISIBLE 확인이 아니라 화면의 보이는 영역에 그려졌는지를 본다.
     *
     * ===> 단말 영역 밖에 그려졌는지 여부 확인
     *
     * result
     * visible : Pass
     * invisible , gone , 단말 영역 밖 : Fail
     */
    @Test
    fun isOnDrawDisplay() {
        onView(withId(R.id.btn_comming_soon))
            .check(matches(isDisplayed()))
    }

    /**
     * withParent(withText("TEXT")) // 부모에게서
     * withChild(withText("TEXT")) // 자식들에게서
     * hasSibling(withText("TEXT")) // 같은 계층의 뷰들
     * hasDescendant(withText("TEXT")) // 하위 계층의 모든 뷰들
     *
     * 관계를 통해 탐색 대상을 지정하는 함수
     */
    @Test
    fun searchRelation() {
        onView(allOf(withId(R.id.ll_root), hasDescendant(withText("Translate"))))
            .check(matches(isDisplayed()))
    }
}