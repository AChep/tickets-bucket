package com.artemchep.ticketsbucket2.contracts

import com.artemchep.mvp.IPresenter
import com.artemchep.mvp.IView
import com.artemchep.mvp.IViewApi
import com.artemchep.ticketsbucket2.expect.IFigure

/**
 * @author Artem Chepurnoy
 */
interface IQrPresenter : IPresenter<IQrViewApi> {
}

/**
 * @author Artem Chepurnoy
 */
interface IQrViewApi : IViewApi {

    fun setLoadingIndicatorShown(isShown: Boolean)

    fun showQrCode(figure: IFigure?)

}
