package uk.co.rossbeazley.ioc_core

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CreatedActivityInjectedWithDependencies {

    @Rule
    @JvmField               /** ick, deprecated :S, lets add to the TODO list */
    var activityTestRule = ActivityTestRule(ThingActivity::class.java, false,false)


    private lateinit var activityIoCContainer: ActivityIoCContainer
    private lateinit var ioCContainer: IoCContainer

    @Before
    fun registersIoCContainer() {


        ioCContainer = IoCContainer()
        activityIoCContainer = ActivityIoCContainer(ioCContainer)

        val applicationContext = getInstrumentation().targetContext.applicationContext as Application
        applicationContext.registerActivityLifecycleCallbacks(activityIoCContainer)
    }

    @Test
    fun createdActivityIsPresentedForDependecyInjection() {

        val specificThing = Thing()
        ioCContainer.register(specificThing)

        val intent = Intent(Intent.ACTION_VIEW)
        activityTestRule.launchActivity(intent)

        assertThat(activityTestRule.activity.thing, `is`(specificThing))
    }


    class ActivityIoCContainer(private val ioCContainer: IoCContainer) : Application.ActivityLifecycleCallbacks {
        private lateinit var thing: Thing

        override fun onActivityCreated(p0: Activity, p1: Bundle?) {
            ioCContainer.injectDependencies(p0)
        }

        override fun onActivityPaused(p0: Activity) {}
        override fun onActivityStarted(p0: Activity) {}
        override fun onActivityDestroyed(p0: Activity) {}
        override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {}
        override fun onActivityStopped(p0: Activity) {}
        override fun onActivityResumed(p0: Activity) {}


    }

    class IoCContainer {
        private lateinit var thing: Thing

        fun register(thing: Thing) {
            this.thing = thing
        }

        fun injectDependencies(into: Any) {
            (into as ThingActivity).thing = thing
        }
    }
}