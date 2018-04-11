package com.artemchep.ticketsbucket.logic

import com.artemchep.ticketsbucket.codecs.ua.UkrQrTicketCodec
import com.artemchep.ticketsbucket.contracts.ITicketDetailsQrPresenter
import com.artemchep.ticketsbucket.contracts.ITicketDetailsQrView
import com.artemchep.ticketsbucket.data.IQrTicket

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
            showCode("000B380B-C0EAEC2F-0001")
            showQrCode(ticketContents)
        }
    }

}