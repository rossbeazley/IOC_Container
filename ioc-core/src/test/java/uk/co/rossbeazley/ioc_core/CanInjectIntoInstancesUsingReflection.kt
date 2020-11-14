package uk.co.rossbeazley.ioc_core

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Test

class CanInjectIntoInstancesUsingReflection {

    @Test
    fun theOneWhereAnInstanceHasDependenciesInjectedThroughSetters() {
        val ioCContainer = ReflectionIoCContainer()
        val somethingThatNeedsAThing = SomethingThatNeedsAThingSimple()

        val specificThing = Thing()
        ioCContainer.register(specificThing)

        ioCContainer.injectDependencies(into = somethingThatNeedsAThing)

        Assert.assertThat(somethingThatNeedsAThing.thing, `is`(specificThing))
    }


    @Test
    fun theOneWhereAnotherInstanceTypeHasDependenciesInjectedThroughSetters() {
        val ioCContainer = ReflectionIoCContainer()
        val somethingElseThatNeedsAThing = SomethingElseThatNeedsAThingSimple()

        val specificThing = Thing()
        ioCContainer.register(specificThing)

        ioCContainer.injectDependencies(into = somethingElseThatNeedsAThing)

        Assert.assertThat(somethingElseThatNeedsAThing.thing, `is`(specificThing))
    }

    @Test
    fun injectingSomeOtherTypeOfThing() {
        val ioCContainer = ReflectionIoCContainer()

        val otherThing = OtherThing()
        ioCContainer.register(otherThing)

        val totherNeedyThing = ThisThingNeedsAnotherThingSimple()
        ioCContainer.injectDependencies(into = totherNeedyThing)

        Assert.assertThat(totherNeedyThing.thing, `is`(otherThing))
    }
}

class ReflectionIoCContainer : IoCContainer {

    private val things = mutableMapOf<Class<out Any>,Any>()
    override fun register(specificThing: Any) {
        things[specificThing::class.java] = specificThing
    }

    override fun injectDependencies(into: Any) {
        val (known, unkown) = into::class.java.fields
            .partition { things.containsKey(it.type) }

        known.forEach { it.set(into,things[it.type]) }
        unkown.forEach {
            it.set(into,it.type.newInstance())
        }
    }
}

class SomethingThatNeedsAThingSimple {
    lateinit var thing: Thing
}

class SomethingElseThatNeedsAThingSimple {
    lateinit var thing: Thing
}


class ThisThingNeedsAnotherThingSimple {
    lateinit var thing: OtherThing
}
