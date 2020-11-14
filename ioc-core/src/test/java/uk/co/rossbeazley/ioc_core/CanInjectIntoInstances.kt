package uk.co.rossbeazley.ioc_core

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Test

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

    override fun register(thing: Thing) {
        this.thing = thing
    }

    override fun injectDependencies(into: Any) {
        (into as SomethingThatNeedsAThing).thing = thing
    }
}

class SomethingThatNeedsAThing {
    lateinit var thing: Thing
}

class SomethingElseThatNeedsAThing {
    lateinit var thing: Thing
}
