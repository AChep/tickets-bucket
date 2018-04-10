package com.artemchep.ticketsbucket.ticket_details_qr

import com.artemchep.ticketsbucket.codecs.ua.UkrQrTicketCodec
import com.artemchep.ticketsbucket.interfaces.IQrTicket

/**
 * @author Artem Chepurnoy
 */
class TicketDetailsQrPresenter(private val ticket: IQrTicket) : ITicketDetailsQrPresenter {

    override var view: ITicketDetailsQrView? = null

    /**
     * Encoded contents of the e-ticket.
     */
    private val ticketContents by lazy {
        val codec = UkrQrTicketCodec()
        return@lazy codec.encode(ticket)
    }

    override fun onStart() {
        view!!.apply {
            showCode(1.hashCode().toString())
            showQrCode(ticketContents)
        }
    }

}