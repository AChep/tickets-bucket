package com.artemchep.ticketsbucket.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.artemchep.ticketsbucket2.contracts.ITicketDetailsQrPresenter
import com.artemchep.ticketsbucket.data.QrTicket
import com.artemchep.ticketsbucket2.logic.TicketDetailsQrPresenter
import com.artemchep.ticketsbucket.view.fragments.TicketDetailsQrFragment


/**
 * @author Artem Chepurnoy
 */
class TicketDetailsQrActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TICKET = "extra::ticket"

        /**
         * Creates new intent with preset
         * required extras.
         */
        fun newIntent(context: Context, ticket: QrTicket): Intent {
            return Intent(context, TicketDetailsQrActivity::class.java).apply {
                putExtra(EXTRA_TICKET, ticket)
            }
        }
    }

    lateinit var presenter : ITicketDetailsQrPresenter

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUI()
        } else showSystemUI()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val ticket = intent!!.getParcelableExtra(EXTRA_TICKET) as QrTicket
        presenter = TicketDetailsQrPresenter(ticket)

        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            return
        }

        val fragment = TicketDetailsQrFragment()
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

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

}