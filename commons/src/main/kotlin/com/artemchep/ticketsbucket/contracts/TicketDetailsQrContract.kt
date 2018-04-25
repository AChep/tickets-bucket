package com.artemchep.ticketsbucket2.contracts

import com.artemchep.mvp.IPresenter
import com.artemchep.mvp.IView
import com.artemchep.mvp.IViewApi

/**
 * @author Artem Chepurnoy
 */
interface ITicketDetailsQrPresenter : IPresenter<ITicketDetailsQrViewApi> {
}

/**
 * @author Artem Chepurnoy
 */
interface ITicketDetailsQrViewApi : IViewApi, IQrViewApi {

    fun showCode(code: String)

    fun showRoute(route: String)

}

/**
 * @author Artem Chepurnoy
 */
interface ITicketDetailsQrView : IView<ITicketDetailsQrViewApi, ITicketDetailsQrPresenter>, ITicketDetailsQrViewApi
