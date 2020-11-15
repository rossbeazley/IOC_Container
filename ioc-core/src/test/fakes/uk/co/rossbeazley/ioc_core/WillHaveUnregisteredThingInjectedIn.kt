package uk.co.rossbeazley.ioc_core

class WillHaveUnregisteredThingInjectedIn {
    lateinit var unregisteredThing : UnregisteredThing
    private var secretthing : UnregisteredThing? = null

    fun secret() : UnregisteredThing? {
        return secretthing
    }

}