package com.artemchep.ticketsbucket.contracts

import com.artemchep.mvp.IPresenter
import com.artemchep.mvp.IView
import com.artemchep.ticketsbucket.expect.IFigure

/**
 * @author Artem Chepurnoy
 */
interface ITicketDetailsQrPresenter : IPresenter<ITicketDetailsQrPresenter, ITicketDetailsQrView> {
}

/**
 * @author Artem Chepurnoy
 */
interface ITicketDetailsQrView : IView<ITicketDetailsQrView, ITicketDetailsQrPresenter> {

    fun setLoadingIndicatorShown(isShown: Boolean)

    fun showQrCode(figure: IFigure?)

    fun showCode(code: String)

}
