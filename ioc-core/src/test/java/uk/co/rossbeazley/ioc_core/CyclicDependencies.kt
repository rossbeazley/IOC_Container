package uk.co.rossbeazley.ioc_core

import org.junit.Test
import uk.co.rossbeazley.ioc_core.IoCContainer.CyclicDependencyException

class CyclicDependencies {

    @Test(expected = CyclicDependencyException::class)
    fun cyclicDependenciesThroughConstructorsNotSupported() {
        val ioCContainer = ReflectionIoCContainer()
        ioCContainer.injectDependencies(into = FailsInjection())
    }

}

class CyclicExample_A(val b : CyclicExample_B){}

class CyclicExample_B(val a : CyclicExample_A){}

class FailsInjection() {
    lateinit var a : CyclicExample_A
}



