package com.artemchep.ticketsbucket

import android.app.Application
import com.artemchep.ticketsbucket.data.Factory
import com.artemchep.ticketsbucket.data.FactoryAndroid

/**
 * @author Artem Chepurnoy
 */
class Heart : Application() {

    override fun onCreate() {
        super.onCreate()

        /*
         * Use Android-specific parcelable models
         * for better performance.
         */
        val factoryAndroid = FactoryAndroid()
        Factory.inject(factoryAndroid)
    }

}