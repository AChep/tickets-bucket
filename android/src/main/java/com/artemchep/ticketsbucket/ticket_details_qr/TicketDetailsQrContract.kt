package com.artemchep.ticketsbucket.ticket_details_qr

import com.artemchep.mvp.IPresenter
import com.artemchep.mvp.IView
import com.artemchep.ticketsbucket.interfaces.IQrTicket

/**
 * @author Artem Chepurnoy
 */
interface ITicketDetailsQrPresenter : IPresenter<ITicketDetailsQrPresenter, ITicketDetailsQrView> {
}

/**
 * @author Artem Chepurnoy
 */
interface ITicketDetailsQrView : IView<ITicketDetailsQrView, ITicketDetailsQrPresenter> {

    fun showQrCode(contents : String)

    fun showCode(code : String)

}
