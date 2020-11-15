package uk.co.rossbeazley.ioc_core

import org.hamcrest.CoreMatchers.`is`
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

    @Test
    fun simpleDependencyAndSingletonDependency() {
        val ioCContainer = ReflectionIoCContainer()
        val thing = Thing()
        ioCContainer.registerSingletonInstance(thing)

        val usesUnregisteredThing = WillHaveThingWithMoreComplexDependenciesInjectedIn()
        ioCContainer.injectDependencies(into = usesUnregisteredThing)

        assertThat(usesUnregisteredThing.thingWithDependencies, notNullValue())
        assertThat(usesUnregisteredThing.thingWithDependencies.dependency, notNullValue())
        assertThat(usesUnregisteredThing.thingWithDependencies.singleton, `is`(thing))
    }

}

