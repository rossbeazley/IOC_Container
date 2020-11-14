package uk.co.rossbeazley.ioc_core

interface IoCContainer {
    fun register(specificThing: Thing)
    fun injectDependencies(into: Any)

}