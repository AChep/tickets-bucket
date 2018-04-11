package com.artemchep.ticketsbucket.contracts

import com.artemchep.mvp.IPresenter
import com.artemchep.mvp.IView

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
