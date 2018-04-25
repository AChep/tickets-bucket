package com.artemchep.ticketsbucket2.contracts

import com.artemchep.mvp.IPresenter
import com.artemchep.mvp.IViewApi
import com.artemchep.ticketsbucket.data.Joint

/**
 * @author Artem Chepurnoy
 */
interface IRoutePresenter : IPresenter<IRouteViewApi> {
}

/**
 * @author Artem Chepurnoy
 */
interface IRouteViewApi : IViewApi {

    fun setRouteLoadingIndicatorShown(isShown: Boolean)

    fun showNoRoute()

    fun showRoute(joints : List<Joint>)

}
