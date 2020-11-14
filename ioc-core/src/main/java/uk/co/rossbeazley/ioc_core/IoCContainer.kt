package uk.co.rossbeazley.ioc_core

interface IoCContainer {
    fun register(specificThing: Any)
    fun injectDependencies(into: Any)

}