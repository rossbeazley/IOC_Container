package uk.co.rossbeazley.ioc_core

import org.hamcrest.CoreMatchers.*
import org.junit.Assert
import org.junit.Test

class InstantiationOfDependencyWithDependencies {

    @Test
    fun theOneWhereAnInstanceHasDependenciesInjectedThroughSetters() {
        val ioCContainer = ReflectionIoCContainer()

        val usesUnregisteredThing = WillHaveThingWithDependenciesInjectedIn()
        ioCContainer.injectDependencies(into = usesUnregisteredThing)

        Assert.assertThat(usesUnregisteredThing.thingWithDependencies, notNullValue())
        Assert.assertThat(usesUnregisteredThing.thingWithDependencies.dependency, notNullValue())
    }

}

