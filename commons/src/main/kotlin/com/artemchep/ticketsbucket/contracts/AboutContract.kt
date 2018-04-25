package com.artemchep.ticketsbucket2.contracts

import com.artemchep.mvp.IPresenter
import com.artemchep.mvp.IView
import com.artemchep.mvp.IViewApi

/**
 * @author Artem Chepurnoy
 */
interface IAboutPresenter : IPresenter<IAboutViewApi> {
}

/**
 * @author Artem Chepurnoy
 */
interface IAboutViewApi : IViewApi {
}

/**
 * @author Artem Chepurnoy
 */
interface IAboutView : IView<IAboutViewApi, IAboutPresenter>, IAboutViewApi
