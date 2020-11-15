package uk.co.rossbeazley.ioc_core

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class CanInjectIntoInstancesUsingReflection {

    lateinit var ioCContainer: ReflectionIoCContainer
    lateinit var specificThing: Thing

    @Before
    fun iOCContainerWithRegisteredDependencies() {
        ioCContainer = ReflectionIoCContainer()
        specificThing = Thing()
        ioCContainer.registerSingletonInstance(specificThing)
    }


    @Test
    fun theOneWhereAnInstanceHasDependenciesInjectedThroughSetters() {
        val somethingThatNeedsAThing = SomethingThatNeedsAThingSimple()
        ioCContainer.injectDependencies(into = somethingThatNeedsAThing)
        assertThat(somethingThatNeedsAThing.thing, `is`(specificThing))
    }


    @Test
    fun theOneWhereAnotherInstanceTypeHasDependenciesInjectedThroughSetters() {
        val somethingElseThatNeedsAThing = SomethingElseThatNeedsAThingSimple()
        ioCContainer.injectDependencies(into = somethingElseThatNeedsAThing)
        assertThat(somethingElseThatNeedsAThing.thing, `is`(specificThing))
    }

    @Test
    fun injectingSomeOtherTypeOfThing() {

        val otherThing = OtherThing()
        ioCContainer.registerSingletonInstance(otherThing)

        val totherNeedyThing = ThisThingNeedsAnotherThingSimple()
        ioCContainer.injectDependencies(into = totherNeedyThing)

        assertThat(totherNeedyThing.thing, `is`(otherThing))
    }
}


