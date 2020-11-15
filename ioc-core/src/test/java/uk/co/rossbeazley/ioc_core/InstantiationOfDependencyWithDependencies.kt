package uk.co.rossbeazley.ioc_core

import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.assertThat
import org.junit.Test

class InstantiationOfDependencyWithDependencies {

    @Test
    fun theOneWhereAnInstanceHasDependenciesInjectedThroughSetters() {
        val ioCContainer = ReflectionIoCContainer()

        val usesUnregisteredThing = WillHaveThingWithDependenciesInjectedIn()
        ioCContainer.injectDependencies(into = usesUnregisteredThing)

        assertThat(usesUnregisteredThing.thingWithDependencies, notNullValue())
        assertThat(usesUnregisteredThing.thingWithDependencies.dependency, notNullValue())
    }

}

