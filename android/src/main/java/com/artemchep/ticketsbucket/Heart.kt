package com.artemchep.ticketsbucket

import android.app.Application

/**
 * @author Artem Chepurnoy
 */
class Heart : Application() {

    override fun onCreate() {
        super.onCreate()

        val holderAndroid = HolderAndroid()
        Holder.inject(holderAndroid)

        /*
         * Use Android-specific parcelable models
         * for better performance.
         */
        val factoryAndroid = FactoryAndroid()
        Factory.inject(factoryAndroid)
    }

}