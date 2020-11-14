package uk.co.rossbeazley.ioc_core

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Test
import uk.co.rossbeazley.ioc_core.NeedsAThing as NeedsAThing

class CanInjectIntoInstances {

    @Test
    fun theOneWhereAnInstanceHasDependenciesInjectedThroughSetters() {
        val ioCContainer = SimpleIoCContainer()
        val somethingThatNeedsAThing = SomethingThatNeedsAThing()

        val specificThing = Thing()
        ioCContainer.register(specificThing)

        ioCContainer.injectDependencies(into = somethingThatNeedsAThing)

        Assert.assertThat(somethingThatNeedsAThing.thing, `is`(specificThing))
    }


    @Test
    fun theOneWhereAnotherInstanceTypeHasDependenciesInjectedThroughSetters() {
        val ioCContainer = SimpleIoCContainer()
        val somethingElseThatNeedsAThing = SomethingElseThatNeedsAThing()

        val specificThing = Thing()
        ioCContainer.register(specificThing)

        ioCContainer.injectDependencies(into = somethingElseThatNeedsAThing)

        Assert.assertThat(somethingElseThatNeedsAThing.thing, `is`(specificThing))
    }
}

class SimpleIoCContainer : IoCContainer {
    private lateinit var thing: Thing

    override fun register(thing: Any) {
        if (thing is Thing) this.thing = thing
    }

    override fun injectDependencies(into: Any) {
        (into as NeedsAThing).thing = thing
    }
}

class SomethingThatNeedsAThing : NeedsAThing {
    override var thing: Thing
        get() = thingStorage
        set(value) {thingStorage=value}

    lateinit var thingStorage: Thing
}

class SomethingElseThatNeedsAThing  : NeedsAThing {
    override var thing: Thing
        get() = thingStorage
        set(value) {thingStorage=value}

    lateinit var thingStorage: Thing

}

interface NeedsAThing {
    var thing: Thing
}