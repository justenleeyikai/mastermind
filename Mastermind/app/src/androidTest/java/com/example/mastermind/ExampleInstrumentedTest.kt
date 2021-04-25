package com.example.mastermind

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    private var intent = Intent(appContext, MainActivity::class.java).putExtra(Companion.EXTRA_ANSWER, "ABA")

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(intent)

    private val orange = appContext.resources.getColor(R.color.orange)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.mastermind", appContext.packageName)
    }

    @Test
    fun initialGameBoardTest() {
        // Context of the app under test.
        checkForInitialGameState()
    }

    @Test
    fun happyPathTest() {
        //assuming Answer = "ABA

        checkForInitialGameState()

        onView(withId(R.id.buttonA)).perform(click())

        onView(withId(R.id.lED3)).check(matches(withBackgroundColor(Color.GREEN)))
        onView(withId(R.id.lED2)).check(matches(withBackgroundColor(Color.GRAY)))
        onView(withId(R.id.lED1)).check(matches(withBackgroundColor(Color.GRAY)))

        onView(withId(R.id.buttonB)).perform(click())

        onView(withId(R.id.lED3)).check(matches(withBackgroundColor(Color.GREEN)))
        onView(withId(R.id.lED2)).check(matches(withBackgroundColor(Color.GREEN)))
        onView(withId(R.id.lED1)).check(matches(withBackgroundColor(Color.GRAY)))

        onView(withId(R.id.buttonA)).perform(click())

        checkForGameWonState()
    }

    @Test
    fun providedScenarioTest() {
        //assuming Answer = "ABA

        checkForInitialGameState()

        onView(withId(R.id.buttonB)).perform(click())

        onView(withId(R.id.lED3)).check(matches(withBackgroundColor(orange)))
        onView(withId(R.id.lED2)).check(matches(withBackgroundColor(Color.GRAY)))
        onView(withId(R.id.lED1)).check(matches(withBackgroundColor(Color.GRAY)))

        onView(withId(R.id.buttonB)).perform(click())

        onView(withId(R.id.lED3)).check(matches(withBackgroundColor(orange)))
        onView(withId(R.id.lED2)).check(matches(withBackgroundColor(Color.GREEN)))
        onView(withId(R.id.lED1)).check(matches(withBackgroundColor(Color.GRAY)))

        onView(withId(R.id.buttonC)).perform(click())

        onView(withId(R.id.lED3)).check(matches(withBackgroundColor(Color.RED)))
        onView(withId(R.id.lED2)).check(matches(withBackgroundColor(Color.GREEN)))
        onView(withId(R.id.lED1)).check(matches(withBackgroundColor(orange)))

        onView(withId(R.id.buttonA)).perform(click())

        onView(withId(R.id.lED3)).check(matches(withBackgroundColor(Color.GREEN)))
        onView(withId(R.id.lED2)).check(matches(withBackgroundColor(Color.RED)))
        onView(withId(R.id.lED1)).check(matches(withBackgroundColor(orange)))

        onView(withId(R.id.buttonB)).perform(click())

        onView(withId(R.id.lED3)).check(matches(withBackgroundColor(orange)))
        onView(withId(R.id.lED2)).check(matches(withBackgroundColor(orange)))
        onView(withId(R.id.lED1)).check(matches(withBackgroundColor(Color.RED)))
    }

    @Test
    fun pressAndHoldTest() {
        //assuming Answer = "ABA

        checkForInitialGameState()

        onView(withId(R.id.buttonB)).perform(longClick())

        onView(withId(R.id.lED3)).check(matches(withBackgroundColor(orange)))
        onView(withId(R.id.lED2)).check(matches(withBackgroundColor(Color.GRAY)))
        onView(withId(R.id.lED1)).check(matches(withBackgroundColor(Color.GRAY)))

        onView(withId(R.id.buttonB)).perform(longClick())

        onView(withId(R.id.lED3)).check(matches(withBackgroundColor(orange)))
        onView(withId(R.id.lED2)).check(matches(withBackgroundColor(Color.GREEN)))
        onView(withId(R.id.lED1)).check(matches(withBackgroundColor(Color.GRAY)))

        onView(withId(R.id.buttonC)).perform(longClick())

        onView(withId(R.id.lED3)).check(matches(withBackgroundColor(Color.RED)))
        onView(withId(R.id.lED2)).check(matches(withBackgroundColor(Color.GREEN)))
        onView(withId(R.id.lED1)).check(matches(withBackgroundColor(orange)))

        onView(withId(R.id.buttonA)).perform(longClick())

        onView(withId(R.id.lED3)).check(matches(withBackgroundColor(Color.GREEN)))
        onView(withId(R.id.lED2)).check(matches(withBackgroundColor(Color.RED)))
        onView(withId(R.id.lED1)).check(matches(withBackgroundColor(orange)))

        onView(withId(R.id.buttonB)).perform(longClick())

        onView(withId(R.id.lED3)).check(matches(withBackgroundColor(orange)))
        onView(withId(R.id.lED2)).check(matches(withBackgroundColor(orange)))
        onView(withId(R.id.lED1)).check(matches(withBackgroundColor(Color.RED)))
    }


    @Test
    fun providedScenarioTest1() {
        //assuming Answer = "AAA"

        var intent = Intent(appContext, MainActivity::class.java).putExtra(Companion.EXTRA_ANSWER, "AAA")

        val activityScenario: ActivityScenario<MainActivity> = ActivityScenario.launch(intent)

        checkForInitialGameState()

        onView(withId(R.id.buttonA)).perform(click())

        onView(withId(R.id.lED3)).check(matches(withBackgroundColor(Color.GREEN)))
        onView(withId(R.id.lED2)).check(matches(withBackgroundColor(Color.GRAY)))
        onView(withId(R.id.lED1)).check(matches(withBackgroundColor(Color.GRAY)))

        onView(withId(R.id.buttonA)).perform(click())

        onView(withId(R.id.lED3)).check(matches(withBackgroundColor(Color.GREEN)))
        onView(withId(R.id.lED2)).check(matches(withBackgroundColor(Color.GREEN)))
        onView(withId(R.id.lED1)).check(matches(withBackgroundColor(Color.GRAY)))

        onView(withId(R.id.buttonA)).perform(click())

        checkForGameWonState()

        onView(withId(R.id.buttonA)).perform(click())

        checkForGameWonState()

        onView(withId(R.id.buttonA)).perform(click())

        checkForGameWonState()
    }

    fun checkForInitialGameState(){
        onView(withId(R.id.lED3)).check(matches(withBackgroundColor(Color.GRAY)))
        onView(withId(R.id.lED2)).check(matches(withBackgroundColor(Color.GRAY)))
        onView(withId(R.id.lED1)).check(matches(withBackgroundColor(Color.GRAY)))
    }

    fun checkForGameWonState(){
        onView(withId(R.id.lED3)).check(matches(withBackgroundColor(Color.GREEN)))
        onView(withId(R.id.lED2)).check(matches(withBackgroundColor(Color.GREEN)))
        onView(withId(R.id.lED1)).check(matches(withBackgroundColor(Color.GREEN)))
    }

    fun withBackgroundColor(colorResourceId: Int): Matcher<View?>? {
        return object : BoundedMatcher<View?, View>(View::class.java) {
            override fun describeTo(description: Description) {
                description.appendText(Integer.toString(colorResourceId))
            }

            override fun matchesSafely(foundView: View): Boolean {
                val currentBackgroundDrawable = (foundView.background as ColorDrawable)

                val currentColorInt = currentBackgroundDrawable.color
                return currentBackgroundDrawable != null && currentColorInt == colorResourceId
            }
        }
    }

    companion object {
        private const val EXTRA_ANSWER = "EXTRA_ANSWER"
    }
}