package uk.co.rossbeazley.ioc_core

import java.lang.RuntimeException

interface IoCContainer {
    fun registerSingletonInstance(specificThing: Any)
    fun injectDependencies(into: Any)

    class CyclicDependencyException : RuntimeException()
}