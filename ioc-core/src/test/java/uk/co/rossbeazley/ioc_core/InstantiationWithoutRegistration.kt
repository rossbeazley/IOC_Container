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

}

class UnregisteredThing {}

class WillHaveUnregisteredThingInjectedIn {
    lateinit var thing : UnregisteredThing
}