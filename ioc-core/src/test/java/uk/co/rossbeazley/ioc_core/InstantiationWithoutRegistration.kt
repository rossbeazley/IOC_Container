package uk.co.rossbeazley.ioc_core

import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.CoreMatchers.nullValue
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class InstantiationWithoutRegistration {

    lateinit var ioCContainer: ReflectionIoCContainer
    @Before
    fun anIoCContainerWithRegisteredDependencies() {
        ioCContainer = ReflectionIoCContainer()
        val specificThing = Thing()
        ioCContainer.registerSingletonInstance(specificThing)

    }

    @Test
    fun theOneWhereAnInstanceHasDependenciesInjectedThroughSetters() {

        val usesUnregisteredThing = WillHaveUnregisteredThingInjectedIn()
        ioCContainer.injectDependencies(into = usesUnregisteredThing)

        assertThat(usesUnregisteredThing.unregisteredThing, notNullValue())
    }

    @Test
    fun privatePropertiesAreIgnored() {
        val usesUnregisteredThing = WillHaveUnregisteredThingInjectedIn()
        ioCContainer.injectDependencies(into = usesUnregisteredThing)

        assertThat(usesUnregisteredThing.secret(), nullValue())
    }

}

