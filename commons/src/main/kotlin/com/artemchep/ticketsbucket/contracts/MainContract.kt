package com.artemchep.ticketsbucket2.contracts

import com.artemchep.mvp.IPresenter
import com.artemchep.mvp.IView
import com.artemchep.mvp.IViewApi
import com.artemchep.ticketsbucket2.data.IQrTicket

/**
 * @author Artem Chepurnoy
 */
interface IMainPresenter : IPresenter<IMainViewApi> {

    fun scanTicket()

    fun scanTicket(contents: String)

    fun archiveTicket(ticket: IQrTicket)

    fun navigateToTicketDetails(ticket: IQrTicket)

    /**
     * Navigates to ticket's QR-code view of
     * details screen.
     */
    fun navigateToTicketDetailsQr(ticket: IQrTicket)

    fun navigateToAbout()

}

/**
 * @author Artem Chepurnoy
 */
interface IMainViewApi : IViewApi {

    fun showTickets(tickets: List<IQrTicket>)

    fun showTicketsScanner()

    fun showTicketScanError()

    fun showTicketDetails(ticket : IQrTicket)

    fun showTicketDetailsQr(ticket : IQrTicket)

    fun showAbout()

}

/**
 * @author Artem Chepurnoy
 */
interface IMainView : IView<IMainViewApi, IMainPresenter>, IMainViewApi
