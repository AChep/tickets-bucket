package com.artemchep.ticketsbucket

import android.app.Application
import com.artemchep.ticketsbucket2.CrossPlatform

/**
 * @author Artem Chepurnoy
 */
class Heart : Application() {

    override fun onCreate() {
        super.onCreate()

        val android = Android()
        CrossPlatform.inject(android)
    }

}