package uk.co.rossbeazley.ioc_core

import android.content.Intent
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CreatedActivityMadeAvailableForInjectionWithDependencies {

    private lateinit var ioCContainer: CaptuingMockIoCContainer

    @Before
    fun registersIoCContainer() {

        ioCContainer = CaptuingMockIoCContainer()
        val applicationContext = getInstrumentation().targetContext.applicationContext
        var activityIoCContainer =
            ActivityIoCContainer(ioCContainer, applicationContext)
    }

    @Test
    fun createdActivityIsPresentedForDependecyInjection() {

        val intent = Intent(Intent.ACTION_VIEW)
        activityTestRule.launchActivity(intent)

        val target = ioCContainer.target as? ThingActivity
        assertThat(target, `is`(activityTestRule.activity))

    }

    @Rule
    @JvmField               /** ick, deprecated :S, lets add to the TODO list */
    var activityTestRule = ActivityTestRule(ThingActivity::class.java, false,false)


    class CaptuingMockIoCContainer : IoCContainer {

        lateinit var target : Any

        override fun registerSingletonInstance(thing: Any) = Unit

        override fun injectDependencies(into: Any) {
            target = into
        }
    }

}