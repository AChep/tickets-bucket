package com.artemchep.ticketsbucket

import android.app.Application

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