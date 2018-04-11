package com.artemchep.ticketsbucket.contracts

import com.artemchep.mvp.IPresenter
import com.artemchep.mvp.IView
import com.artemchep.ticketsbucket.data.IQrTicket

/**
 * @author Artem Chepurnoy
 */
interface IMainPresenter : IPresenter<IMainPresenter, IMainView> {

    fun scanTicket()

    fun scanTicket(contents: String)

    /**
     * Navigates to ticket's QR-code view of
     * details screen.
     */
    fun navigateToTicketDetailsQr(ticket: IQrTicket)

}

/**
 * @author Artem Chepurnoy
 */
interface IMainView : IView<IMainView, IMainPresenter> {

    fun showTickets(tickets: List<IQrTicket>)

    fun showTicketsScanner()

    fun showTicketScanError()

    fun showTicketDetailsQr(ticket : IQrTicket)

}
