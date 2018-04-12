package com.artemchep.ticketsbucket

import com.artemchep.ticketsbucket.data.IQrTicket

/**
 * Platform-independent factory for models. This class should be removed once Kotlin
 * adds support of nested `expected` modules.
 *
 * @author Artem Chepurnoy
 */
interface Factory {

    companion object : Factory {

        lateinit var FACTORY: Factory
            private set

        fun inject(factory: Factory) {
            FACTORY = factory
        }

        override fun createQrTicket(): IQrTicket = FACTORY.createQrTicket()

    }

    fun createQrTicket(): IQrTicket

}