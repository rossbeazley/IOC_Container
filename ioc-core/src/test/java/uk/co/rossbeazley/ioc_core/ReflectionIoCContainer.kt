package uk.co.rossbeazley.ioc_core

class ReflectionIoCContainer : IoCContainer {

    private val things = mutableMapOf<Class<out Any>,Any>()
    override fun register(specificThing: Any) {
        things[specificThing::class.java] = specificThing
    }

    override fun injectDependencies(into: Any) {
        val (known, unkown) = into::class.java.fields
            .partition { things.containsKey(it.type) }

        known.forEach { it.set(into,things[it.type]) }
        unkown.forEach {
            val newInstance = createInstanceForField(ofType = it.type)
            it.set(into, newInstance)
        }
    }

    private fun createInstanceForField(ofType: Class<*>): Any? {
        val declaredConstructors = ofType.declaredConstructors

        return when(declaredConstructors.size) {
            0 -> ofType.newInstance()
            1 -> {
                val constructor = declaredConstructors[0]
                val args = constructor.parameterTypes
                    .map {it.newInstance()}
                    .toTypedArray()
                constructor.newInstance(*args)
            }
            else -> null
        }
    }
}