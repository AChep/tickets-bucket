package com.artemchep.ticketsbucket.main

import com.artemchep.mvp.IPresenter
import com.artemchep.mvp.IView
import com.artemchep.ticketsbucket.interfaces.QrTicket

/**
 * @author Artem Chepurnoy
 */
interface MainPresenterBase : IPresenter<MainPresenterBase, MainViewBase> {

    fun scanTicket()

}

/**
 * @author Artem Chepurnoy
 */
interface MainViewBase : IView<MainViewBase, MainPresenterBase> {

    fun showTickets(tickets: List<QrTicket>)

    fun showTicketsScanner()

}
