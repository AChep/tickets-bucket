package com.artemchep.ticketsbucket.ticket_details_qr

import android.support.v4.app.Fragment
import com.artemchep.ticketsbucket.interfaces.IQrTicket

/**
 * @author Artem Chepurnoy
 */
class TicketDetailsQrFragment : Fragment(), ITicketDetailsQrView {

    override lateinit var presenter: ITicketDetailsQrPresenter

    override fun showTicket(ticket: IQrTicket) {
    }

}