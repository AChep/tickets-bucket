package com.artemchep.ticketsbucket2.contracts

import com.artemchep.mvp.IPresenter
import com.artemchep.mvp.IView
import com.artemchep.ticketsbucket2.data.IQrTicket

/**
 * @author Artem Chepurnoy
 */
interface IAboutPresenter : IPresenter<IAboutPresenter, IAboutView> {
}

/**
 * @author Artem Chepurnoy
 */
interface IAboutView : IView<IAboutView, IAboutPresenter> {
}
