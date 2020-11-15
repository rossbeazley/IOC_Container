package uk.co.rossbeazley.ioc_core

import org.hamcrest.CoreMatchers.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class InstantiationWithoutRegistration {

    lateinit var ioCContainer: ReflectionIoCContainer
    @Before
    fun anIoCContainerWithRegisteredDependencies() {
        ioCContainer = ReflectionIoCContainer()
        val specificThing = Thing()
        ioCContainer.register(specificThing)

    }

    @Test
    fun theOneWhereAnInstanceHasDependenciesInjectedThroughSetters() {

        val usesUnregisteredThing = WillHaveUnregisteredThingInjectedIn()
        ioCContainer.injectDependencies(into = usesUnregisteredThing)

        Assert.assertThat(usesUnregisteredThing.unregisteredThing, notNullValue())
    }

    @Test
    fun privatePropertiesAreIgnored() {
        val usesUnregisteredThing = WillHaveUnregisteredThingInjectedIn()
        ioCContainer.injectDependencies(into = usesUnregisteredThing)

        Assert.assertThat(usesUnregisteredThing.secret(), nullValue())
    }

}

