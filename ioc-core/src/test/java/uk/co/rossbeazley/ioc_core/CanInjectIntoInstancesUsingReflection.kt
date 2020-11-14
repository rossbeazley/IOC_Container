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
    private lateinit var thing: Thing
    private lateinit var otherThing: OtherThing

    override fun register(thing: Any) {
        when(thing) {
            is Thing -> this.thing = thing
            is OtherThing -> this.otherThing = thing
        }
    }


    override fun injectDependencies(into: Any) {
        val clz = into::class.java

        clz.fields.forEach {
            when(it.type) {
                Thing::class.java -> it.set(into, thing)
                OtherThing::class.java -> it.set(into, otherThing)
            }
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
