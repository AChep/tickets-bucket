package com.artemchep.ticketsbucket2.contracts

import com.artemchep.mvp.IPresenter
import com.artemchep.mvp.IView
import com.artemchep.ticketsbucket2.data.IQrTicket
import com.artemchep.mvp.IViewApi

/**
 * @author Artem Chepurnoy
 */
interface ITicketDetailsPresenter : IPresenter<ITicketDetailsViewApi> {
}

/**
 * @author Artem Chepurnoy
 */
interface ITicketDetailsViewApi : IViewApi, IQrViewApi, IRouteViewApi {

    fun showTicket(ticket: IQrTicket)

}

/**
 * @author Artem Chepurnoy
 */
interface ITicketDetailsView : IView<ITicketDetailsViewApi, ITicketDetailsPresenter>, ITicketDetailsViewApi
