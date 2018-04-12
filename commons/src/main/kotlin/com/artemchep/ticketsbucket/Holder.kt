package com.artemchep.ticketsbucket

import kotlin.coroutines.experimental.CoroutineContext

/**
 * @author Artem Chepurnoy
 */
interface Holder {

    companion object : Holder {
        lateinit var FACTORY: Holder
            private set

        fun inject(factory: Holder) {
            FACTORY = factory
        }

        override val coroutineContextUi = FACTORY.coroutineContextUi

    }

    val coroutineContextUi : CoroutineContext

}