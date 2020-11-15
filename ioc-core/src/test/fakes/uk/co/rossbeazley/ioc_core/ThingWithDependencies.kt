package uk.co.rossbeazley.ioc_core

class ThingWithDependencies(val dependency : String) {}

class ThingWithDependenciesAndSingletonType(val dependency : String, val singleton : Thing) {}