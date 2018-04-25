package com.artemchep.ticketsbucket.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.artemchep.ticketsbucket.data.QrTicket
import com.artemchep.ticketsbucket.view.fragments.TicketDetailsFragment
import com.artemchep.ticketsbucket2.contracts.ITicketDetailsPresenter
import com.artemchep.ticketsbucket2.contracts.ITicketDetailsQrPresenter
import com.artemchep.ticketsbucket2.logic.TicketDetailsPresenter
import com.artemchep.ticketsbucket2.logic.TicketDetailsQrPresenter

/**
 * @author Artem Chepurnoy
 */
class TicketDetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TICKET = "extra::ticket"

        /**
         * Creates new intent with preset
         * required extras.
         */
        fun newIntent(context: Context, ticket: QrTicket): Intent {
            return Intent(context, TicketDetailsActivity::class.java).apply {
                putExtra(EXTRA_TICKET, ticket)
            }
        }
    }

    lateinit var presenter : ITicketDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        val ticket = intent!!.getParcelableExtra(EXTRA_TICKET) as QrTicket
        presenter = TicketDetailsPresenter(ticket)

        super.onCreate(savedInstanceState)

        val fragment = TicketDetailsFragment()
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