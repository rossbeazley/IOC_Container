package uk.co.rossbeazley.ioc_core

class WillHaveThingWithDependenciesInjectedIn {
    lateinit var thingWithDependencies : ThingWithDependencies
}


class WillHaveThingWithMoreComplexDependenciesInjectedIn {
    lateinit var thingWithDependencies : ThingWithDependenciesAndSingletonType
}