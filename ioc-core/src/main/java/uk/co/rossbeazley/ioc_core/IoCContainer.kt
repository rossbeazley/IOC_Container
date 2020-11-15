package uk.co.rossbeazley.ioc_core

interface IoCContainer {
    fun registerSingletonInstance(specificThing: Any)
    fun injectDependencies(into: Any)

}