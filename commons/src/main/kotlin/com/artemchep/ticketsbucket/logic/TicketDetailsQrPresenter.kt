package com.artemchep.ticketsbucket2.logic

import com.artemchep.ticketsbucket2.codecs.ua.UkrQrTicketCodec
import com.artemchep.ticketsbucket2.contracts.ITicketDetailsQrPresenter
import com.artemchep.ticketsbucket2.contracts.ITicketDetailsQrViewApi
import com.artemchep.ticketsbucket2.data.IQrTicket

/**
 * @author Artem Chepurnoy
 */
class TicketDetailsQrPresenter(private val ticket: IQrTicket) : ITicketDetailsQrPresenter {

    override var view: ITicketDetailsQrViewApi? = null
        set(value) {
            field = value
            qrPresenter.view = value
        }

    private val qrPresenter = QrPresenter(UkrQrTicketCodec().encode(ticket))

    override fun onStart() {
        super.onStart()
        view!!.apply {
            showCode("000B380B-C0EAEC2F-0001")
            showRoute("Киев Пассажирский - Днепропетровск Главный")
        }

        qrPresenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        qrPresenter.onResume()
    }

    override fun onPause() {
        qrPresenter.onPause()
        super.onPause()
    }

    override fun onStop() {
        qrPresenter.onStop()
        super.onStop()
    }

}