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

    private lateinit var ioCContainer: CaptuingMockIoCContainer

    @Before
    fun registersIoCContainer() {

        ioCContainer = CaptuingMockIoCContainer()
        var activityIoCContainer = ActivityIoCContainer(ioCContainer)

        val applicationContext = getInstrumentation().targetContext.applicationContext as Application
        applicationContext.registerActivityLifecycleCallbacks(activityIoCContainer)
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



    class ActivityIoCContainer(private val ioCContainer: IoCContainer) : Application.ActivityLifecycleCallbacks {

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

    class CaptuingMockIoCContainer : IoCContainer {

        lateinit var target : Any

        override fun register(thing: Thing) = Unit

        override fun injectDependencies(into: Any) {
            target = into
        }
    }

}