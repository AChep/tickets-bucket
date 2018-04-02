package com.artemchep.ticketsbucket.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * @author Artem Chepurnoy
 */
class MainActivity : AppCompatActivity() {

    val presenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            return
        }

        val fragment = MainFragment()
        fragment.presenter = presenter
        supportFragmentManager
                .beginTransaction()
                .replace(android.R.id.content, fragment)
                .commit()

        presenter.view = fragment
    }

    override fun onDestroy() {
        presenter.view = null
        super.onDestroy()
    }

}