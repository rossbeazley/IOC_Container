package uk.co.rossbeazley.ioc_core

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle

class ActivityIoCContainer(
    private val ioCContainer: IoCContainer = ReflectionIoCContainer(),
    applicationContext: Context
) :
    Application.ActivityLifecycleCallbacks, IoCContainer {

    init {
        (applicationContext.applicationContext as Application).registerActivityLifecycleCallbacks(this)
    }

    override fun registerSingletonInstance(specificThing: Any) {
        ioCContainer.registerSingletonInstance(specificThing)
    }

    override fun injectDependencies(into: Any) {
        ioCContainer.injectDependencies(into)
    }

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