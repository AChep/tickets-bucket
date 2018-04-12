package com.artemchep.ticketsbucket.activities.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.artemchep.mvp.IPresenter
import java.util.*

private typealias PresenterMap = Map<String, IPresenter<*, *>>

/**
 * @author Artem Chepurnoy
 */
abstract class ActivityBase : AppCompatActivity() {

    private lateinit var presenters : PresenterMap

    override fun onCreate(savedInstanceState: Bundle?) {
        presenters = lastNonConfigurationInstance as? PresenterMap ?: TreeMap()
        super.onCreate(savedInstanceState)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any = presenters

}