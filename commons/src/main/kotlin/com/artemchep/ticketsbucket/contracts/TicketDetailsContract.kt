package com.artemchep.ticketsbucket2.contracts

import com.artemchep.mvp.IPresenter
import com.artemchep.mvp.IView
import com.artemchep.ticketsbucket2.data.IQrTicket

/**
 * @author Artem Chepurnoy
 */
interface ITicketDetailsPresenter : IPresenter<ITicketDetailsPresenter, ITicketDetailsView> {
}

/**
 * @author Artem Chepurnoy
 */
interface ITicketDetailsView : IView<ITicketDetailsView, ITicketDetailsPresenter> {

    fun showTicket(ticket: IQrTicket)

}
