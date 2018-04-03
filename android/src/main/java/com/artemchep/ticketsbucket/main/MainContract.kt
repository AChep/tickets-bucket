package com.artemchep.ticketsbucket.main

import com.artemchep.mvp.IPresenter
import com.artemchep.mvp.IView
import com.artemchep.ticketsbucket.interfaces.IQrTicket

/**
 * @author Artem Chepurnoy
 */
interface IMainPresenter : IPresenter<IMainPresenter, IMainView> {

    fun scanTicket()

    fun scanTicket(contents : String)

}

/**
 * @author Artem Chepurnoy
 */
interface IMainView : IView<IMainView, IMainPresenter> {

    fun showTickets(tickets: List<IQrTicket>)

    fun showTicketsScanner()

}
