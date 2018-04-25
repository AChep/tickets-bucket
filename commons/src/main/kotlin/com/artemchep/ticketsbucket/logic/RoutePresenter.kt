package com.artemchep.ticketsbucket2.logic

import com.artemchep.CrossPlatform
import com.artemchep.ticketsbucket.data.Joint
import com.artemchep.ticketsbucket2.contracts.IRoutePresenter
import com.artemchep.ticketsbucket2.contracts.IRouteViewApi
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

/**
 * @author Artem Chepurnoy
 */
class RoutePresenter(
        private val departureStationNumber: String,
        private val arrivalStationNumber: String,
        private val trainNumber: String,

        /**
         * Date must be in `YYYY-MM-dd` format
         */
        private val date: String
) : IRoutePresenter {

    override var view: IRouteViewApi? = null

    private var joints: List<Joint>? = null

    private var job: Job? = null

    override fun onStart() {
        view!!.apply {
            processJoints(this, joints)
        }

        if (joints == null) {
            view!!.setRouteLoadingIndicatorShown(true)

            // Start a job of generating QR-code for this
            // ticket.
            job?.cancel()
            if (job == null) {
                job = loadQrAsyncAndShow(departureStationNumber, arrivalStationNumber, trainNumber, date)
            }
        } else view!!.setRouteLoadingIndicatorShown(false)
    }

    /** Starts a job of loading the QR-code of `contents`. */
    private fun loadQrAsyncAndShow(
            departureStationNumber: String,
            arrivalStationNumber: String,
            trainNumber: String,
            date: String) = launch(CrossPlatform.coroutineContextUi) {
        // Pull the route from Ukr Railways open
        // APIs
        val response = async {
            CrossPlatform.createHttpPost().post(
                    url = "https://booking.uz.gov.ua/ua/route/",
                    params = listOf(
                            "routes[0][from]" to departureStationNumber,
                            "routes[0][to]" to arrivalStationNumber,
                            "routes[0][date]" to date,
                            "routes[0][train]" to trainNumber
                    )
            )
        }.await()

        joints = CrossPlatform
                .createJson()
                .from(response.toString())
                ?.obj("data")
                ?.array("routes")
                ?.at(0)
                ?.array("list")
                ?.run {
                    (0 until size())
                            .map {
                                val json = at(it)
                                return@map Joint(
                                        name = json.string("name"),
                                        code = json.int("code"),
                                        arrivalTime = json.string("arrivalTime"),
                                        departureTime = json.string("departureTime"),
                                        distance = json.string("distance"),
                                        latitude = json.double("lat"),
                                        longitude = json.double("long")
                                )
                            }
                            .toList()
                }

        view?.apply {
            setRouteLoadingIndicatorShown(false)
            processJoints(this, joints)
        }
    }

    private fun processJoints(view: IRouteViewApi, joints: List<Joint>?) {
        if (joints != null) {
            view.showRoute(joints)
        } else view.showNoRoute()
    }

}

