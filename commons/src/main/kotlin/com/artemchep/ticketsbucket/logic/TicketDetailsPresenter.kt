package com.artemchep.ticketsbucket2.logic

import com.artemchep.ticketsbucket2.codecs.ua.UkrQrTicketCodec
import com.artemchep.ticketsbucket2.contracts.ITicketDetailsPresenter
import com.artemchep.ticketsbucket2.contracts.ITicketDetailsViewApi
import com.artemchep.ticketsbucket2.data.IQrTicket

/**
 * @author Artem Chepurnoy
 */
class TicketDetailsPresenter(private val ticket: IQrTicket) : ITicketDetailsPresenter {

    override var view: ITicketDetailsViewApi? = null
        set(value) {
            field = value
            qrPresenter.view = value
            routePresenter.view = value
        }

    private val qrPresenter = QrPresenter(UkrQrTicketCodec().encode(ticket))
    private val routePresenter = RoutePresenter(
            arrivalStationNumber = "2218300",
            departureStationNumber = "2218000",
            trainNumber = "747 КА ФІРМ ІС",
            date = "2018-04-25"
    )

    override fun onStart() {
        super.onResume()
        qrPresenter.onStart()
        routePresenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        qrPresenter.onResume()
        routePresenter.onResume()
    }

    override fun onPause() {
        routePresenter.onPause()
        qrPresenter.onPause()
        super.onPause()
    }

    override fun onStop() {
        routePresenter.onStop()
        qrPresenter.onStop()
        super.onStop()
    }

}