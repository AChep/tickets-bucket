package com.artemchep

import com.artemchep.json.IJson
import com.artemchep.ticketsbucket.expect.IHttpPost
import com.artemchep.ticketsbucket2.data.IQrTicket
import com.artemchep.ticketsbucket2.expect.IQrGenerator
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Platform-independent factory.
 *
 * @author Artem Chepurnoy
 */
interface CrossPlatform {

    companion object : CrossPlatform {

        lateinit var PLATFORM: CrossPlatform
            private set

        fun inject(factory: CrossPlatform) {
            PLATFORM = factory
        }

        override val coroutineContextUi
            get() = PLATFORM.coroutineContextUi

        override fun createQrTicket() = PLATFORM.createQrTicket()

        override fun createQrGenerator() = PLATFORM.createQrGenerator()

        override fun createHttpPost() = PLATFORM.createHttpPost()

        override fun createJson() = PLATFORM.createJson()

    }

    val coroutineContextUi: CoroutineContext

    fun createQrTicket(): IQrTicket

    fun createQrGenerator(): IQrGenerator

    fun createHttpPost(): IHttpPost

    fun createJson(): IJson

}