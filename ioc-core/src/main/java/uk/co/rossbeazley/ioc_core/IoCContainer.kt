package uk.co.rossbeazley.ioc_core

interface IoCContainer {
    fun register(specificThing: Thing)
    fun register(otherThing: OtherThing)
    fun injectDependencies(into: Any)

}