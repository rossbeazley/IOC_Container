package uk.co.rossbeazley.ioc_core

import org.hamcrest.CoreMatchers.*
import org.junit.Assert
import org.junit.Test

class InstantiationWithoutRegistration {

    @Test
    fun theOneWhereAnInstanceHasDependenciesInjectedThroughSetters() {
        val ioCContainer = ReflectionIoCContainer()

        val specificThing = Thing()
        ioCContainer.register(specificThing)

        val usesUnregisteredThing = WillHaveUnregisteredThingInjectedIn()
        ioCContainer.injectDependencies(into = usesUnregisteredThing)

        Assert.assertThat(usesUnregisteredThing.thing, notNullValue())
    }

    @Test
    fun privatePropertiesAreIgnored() {
        val ioCContainer = ReflectionIoCContainer()

        val specificThing = Thing()
        ioCContainer.register(specificThing)

        val usesUnregisteredThing = WillHaveUnregisteredThingInjectedIn()
        ioCContainer.injectDependencies(into = usesUnregisteredThing)

        Assert.assertThat(usesUnregisteredThing.secret(), nullValue())
    }

}

