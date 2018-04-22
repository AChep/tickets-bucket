package com.artemchep.ticketsbucket

import com.artemchep.ticketsbucket.data.IQrTicket
import com.artemchep.ticketsbucket.expect.IQrGenerator
//import kotlin.coroutines.experimental.CoroutineContext

/**
 * Platform-independent factory.
 *
 * @author Artem Chepurnoy
 */
interface CrossPlatform {

    companion object : CrossPlatform {

        lateinit var FACTORY: CrossPlatform
            private set

        fun inject(factory: CrossPlatform) {
            FACTORY = factory
        }

//        override val coroutineContextUi = FACTORY.coroutineContextUi

        override fun createQrTicket(): IQrTicket = FACTORY.createQrTicket()

        override fun createQrGenerator(): IQrGenerator = FACTORY.createQrGenerator()

    }

//    val coroutineContextUi: CoroutineContext

    fun createQrTicket(): IQrTicket

    fun createQrGenerator(): IQrGenerator

}