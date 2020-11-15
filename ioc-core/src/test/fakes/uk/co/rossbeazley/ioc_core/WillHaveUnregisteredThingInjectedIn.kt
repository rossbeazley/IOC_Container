package uk.co.rossbeazley.ioc_core

class WillHaveUnregisteredThingInjectedIn {
    lateinit var thing : UnregisteredThing
    private var secretthing : UnregisteredThing? = null

    fun secret() : UnregisteredThing? {
        return secretthing
    }

}